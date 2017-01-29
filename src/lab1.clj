(ns lab1
  (:use clojure.test))

(defn conj*
  [s x]
  (conj (vec s) x))

(defn permutation [data, n]
  (reduce
    (fn [result, i]
      (apply concat
        (map
          (fn [result-element]
            (map
              (fn [data-element]
                (if (= i 0)
                  (list result-element data-element)
                  (conj* result-element data-element)))
              (filter
                (fn [element]
                  (if (= i 0)
                    (not=
                      result-element
                      element)
                    (not=
                      (last result-element)
                      element))
                  )
                data)))
          result)))
    data
    (range (- n 1))))

(deftest tests
  (testing "lab1 tests"
    (is (= (permutation (list "a" "b" "c") 3)
           (list ["a" "b" "a"] ["a" "b" "c"] ["a" "c" "a"] ["a" "c" "b"] ["b" "a" "b"] ["b" "a" "c"] ["b" "c" "a"] ["b" "c" "b"] ["c" "a" "b"] ["c" "a" "c"] ["c" "b" "a"] ["c" "b" "c"])))
    (is (= (permutation (list "a" "b" 4) 3)
           (list ["a" "b" "a"] ["a" "b" 4] ["a" 4 "a"] ["a" 4 "b"] ["b" "a" "b"] ["b" "a" 4] ["b" 4 "a"] ["b" 4 "b"] [4 "a" "b"] [4 "a" 4] [4 "b" "a"] [4 "b" 4])))
    (is (= (permutation (list "a" 11 false) 3)
           (list ["a" 11 "a"] ["a" 11 false] ["a" false "a"] ["a" false 11] [11 "a" 11] [11 "a" false] [11 false "a"] [11 false 11] [false "a" 11] [false "a" false] [false 11 "a"] [false 11 false])))
    (is (= (permutation (list "a" 11 [33 44]) 3)
           (list ["a" 11 "a"] ["a" 11 [33 44]] ["a" [33 44] "a"] ["a" [33 44] 11] [11 "a" 11] [11 "a" [33 44]] [11 [33 44] "a"] [11 [33 44] 11] [[33 44] "a" 11] [[33 44] "a" [33 44]] [[33 44] 11 "a"] [[33 44] 11 [33 44]])))
    ))

(tests)