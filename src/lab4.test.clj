(ns

  lab4tests
  (:use lab4)
  (:require [clojure.test :as test]))

(test/deftest lab4tests
  (test/testing "lab4 tests"
    (test/is (= 4 (+ 2 2)))

    (test/is (= (constant true) (solve-subst (run (_imp (_and (variable :a) (_not (_or (_imp (variable :a) (variable :b)) (variable :c)))) (_and (variable :c) (variable :c) (variable :a)))))))

    (test/is (= (_andg
                  (_not (variable :x))
                  (_not (variable :y)))
               (de-morg (_not (_or (variable :x) (variable :y))))))

    (test/is (= (variable :z)
                (disc-not (_not (_not (variable :z))))))

    (test/is (= (run (variable :x)) (variable :x)))
    (test/is (= (run (_imp (variable :x) (variable :y))) (_or (_not (variable :x)) (variable :y))))
    (test/is (= (run (_and (_or (variable :a) (variable :b)) (_or (variable :x) (variable :y))))
           (_or (_and (variable :a) (variable :x))
                (_and (variable :a) (variable :y))
                (_and (variable :b) (variable :x))
                (_and (variable :b) (variable :y)))))
    (test/is (= (run (constant true)) (constant true)))
    (test/is (= (run (constant false)) (constant false)))
    (test/is (= (run (_imp (_imp (variable :x) (_or (variable :z) (_not (variable :x)) (variable :z))) (_imp (variable :z) (variable :y))))
                (_or (_and (variable :x) (_not (variable :z)) (variable :x) (_not (variable :z))) (_not (variable :z)) (variable :y)))
             )
    )
  )
(test/run-tests)



