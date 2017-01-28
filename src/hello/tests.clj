(ns lab1.tests
  (:use clojure.test)
  (:use lab1.core)
  )

(deftest tests
  
  (is (= nil 
         (lab1.core/permut [1 2 3] 0)))
  (is (= `((1) (2) (3)) 
         (lab1.core/permut [1 2 3] 1)))
  (is (= `((2 1) (3 1) (1 2) (3 2) (1 3) (2 3)) 
         (lab1.core/permut [1 2 3] 2)))
  (is (= 12 
         (count (lab1.core/permut [1 2 3] 3))))
  (is (every? #(= (count %) 4) (lab1.core/permut [1 2 3] 4))
      )
  )