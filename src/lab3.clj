(ns lab3
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

(defn integr-lazy [func]
  (let [n 10 h (/ 1 n)]
    (letfn [
      (sum-count [i localSum]
        (let [temp (+ localSum (func (* i h)))]
          (cons temp (lazy-seq (sum-count (inc i) temp)))
        ))]
      (let [answer (sum-count 0 0)] 
        (fn [x]
          (* h (+ (nth answer (- (/ x h) 1)) (/ (+ (func 0) (func x)) 2)))
        )
      )
    )
  )
)

(defn main []
  (letfn [(f [x] (* x x))]
    (println "no laziness")
    (let [func (integr f)]
      (time (func 10))
      (time (func 10))
      (time (func 16)))
    (println "laziness")
    (let [func (integr-lazy f)]
      (time (func 10))
      (time (func 10))
      (time (func 16)))
  )
)

(defn difference ^double [^double x ^double y]
  (Math/abs (double (- x y)))
  )

(defn close [x y]
  (< (difference x y) 0.2)
  )

(deftest tests
  (is (close 
    (let [func (integr-lazy (fn [x] (* x x)))]
       (func 1)) 1/3
    )      
  )
)
; (tests)
(main)
