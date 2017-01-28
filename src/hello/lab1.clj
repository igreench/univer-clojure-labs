(ns rrlab1
  (:use clojure.test))

(defn permutation-internal [a b n]
  (if (= n 1)
    a
    (if (= n 0)
      nil
      (let [temp (for [x a y b
        ; :when (not (= (first x) y))
        ]
        (conj x y))]
        (if (some #(< (count %) n) temp)
          (permutation-internal temp b n)
          temp
        )
      )
    )
  )
)

(defn permutation [a n]
  (permutation-internal (map list a) a n))

(defn main []
  (println (permutation [1 2 3] 3))
  (println (permutation ["a" "b" "c"] 3))
  (println (permutation ["a" 1 true] 3))
  (println (permutation ["a" 1 [1, 2, 3]] 3)))

(deftest tests
  (is (= nil
    (permutation [1 2 3] 0)))
  (is (= `((1) (2) (3))
    (permutation [1 2 3] 1)))
  (is (= `((2 1) (3 1) (1 2) (3 2) (1 3) (2 3)) 
    (permutation [1 2 3] 2)))
  (is (= 12 
    (count (permutation [1 2 3] 3))))
  (is (every? #(= (count %) 4)
    (permutation [1 2 3] 4))))

;(tests)
(main)