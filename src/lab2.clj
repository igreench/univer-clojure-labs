(ns lab2
  (:use clojure.test))

(defn integr [func]
  (fn [x]
    (let [n 10 h (/ 1 n)]
      (letfn [
        (sum-count [i localSum]
          (if (< (* i h) x)
            (sum-count (inc i) (+ localSum (func (* i h))))
            localSum))]
        (let [sum (sum-count 1 0)]
          (* h (+ sum (/ (+ (func 0) (func x)) 2)))
        )
      )
    )
  )
)

(defn integr-mem [func]
  (let [n 10 h (/ 1 n)]
    (letfn [
      (sum-count [i self-mem]
        (let [localSum (func (* i h))]
          (if (> i 0)
            (+ localSum (self-mem (dec i) self-mem))
            localSum)))]
      (let [sum-memo (memoize sum-count)]
        (fn [x]
          (* h (+ (sum-memo (int (/ x h)) sum-memo) (/ (+ (func 0) (func x)) 2)))
        )
      )
    )
  )
)

(defn main []
  (println "no memoize")
  (letfn [(f [x] (* x x))]
    (let [func (integr f)]
      (time (func 10))
      (time (func 10))
      (time (func 16)))
    (println "memoize")
    (let [func (integr-mem f)]
      (time (func 10))
      (time (func 10))
      (time (func 16))
    )
    ((integr f) 2)
  )
)

(defn difference ^double [^double x ^double y]
  (Math/abs (double (- x y))))

(defn close [x y]
  (< (difference x y) 0.2))

(deftest tests
  (is (close
    (let [func (integr-mem (fn [x] (* x x)))]
      (func 1)) 1/3)))

 (tests)
;(main)