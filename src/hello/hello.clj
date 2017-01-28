(ns hello.hello)

(ns HelloWorld)

(defn myFunction
  "Description of the method"
  [x]
  (println x "! Hello, World!"))

(println "BEGIN")

;(println (myFunction "test"))

(defn flatten-one-level [coll]
  (mapcat  #(if (sequential? %) % [%]) coll))

;(defn flatten-one-level [coll]
;  coll)

;(defn permutation
;  [array, max-length]
;  (reduce
;    (fn [result, i]
;      (println result)
;      (println i)
;      (map
;        (fn [result-element]
;          (cons (flatten-one-level result) result-element)
;          )
;        (filter
;          (fn [array-element]
;            true)
;          array))
;      )
;    (seq [])
;    (range max-length)))


;(map
;  (fn [result-element]
;    (println result-element)
;    (println i)
;    (map
;      (fn [array-element]
;        (if (= i 0)
;          (seq [result-element, array-element])
;          (cons result-element array-element)))
;      (filter
;        (fn [array-element]
;          (if (= i 0)
;            (not= result-element array-element)
;            (not= (last result-element) array-element))
;          )
;        array))
;    )
;  (filter
;    (fn [arr-el]
;      true)
;    result-element)
;  )

;(defn permutation
;  [array, max-length]
;  (reduce
;    (fn [result-element, i]
;      (println result-element)
;      (println i)
;      (map
;        (fn [array-element]
;          (if (= i 0)
;            (seq [result-element, array-element])
;            (cons result-element array-element)))
;        (filter
;          (fn [array-element]
;            (if (= i 0)
;              (not= result-element array-element)
;              (not= (last result-element) array-element))
;            )
;          array))
;      )
;    (seq array)
;    (range max-length)))

(defn permutation
  [array, max-length]
  (reduce
    (fn [result-element, i]
      (println result-element)
      (println i)

      ;(mapcat
      ;
      ;  )

      (map
        (fn [array-element]
          ;(println "result-element: " result-element)
          ;(println "flatten result-element: " (flatten result-element))
          ;(println "array-element: " array-element)
          (if (= i 0)
            (seq [result-element, array-element])
            (cons result-element array-element)))
        (filter
          (fn [array-element]
            ;(println "res-el" res-el)
            ;(println "array-element" array-element)
            (if (= i 0)
              (not= result-element array-element)
              (not= (last result-element) array-element))
            )
          array))




      ;(map
      ;  (fn [result-element]
      ;
      ;
      ;    ;(if (= i 0)
      ;    ;  (seq [array-element])
      ;    ;  (conj result array-element))
      ;    )
      ;  (filter
      ;    (fn [array-element]
      ;      ;(not= (last result) array-element)
      ;      true
      ;      ;(if (= i 0)
      ;      ;  (not= result array-element)
      ;      ;  (not= (last result) array-element))
      ;      )
      ;    result))
      )
    (list [])
    (range max-length)))

(println (permutation ["a", "b", "c"] 3))

;(defn permutation
;  "Function of permutations"
;  [array, max-length]
;  (println array)
;  (println max-length)
;  (reduce
;    (fn [result, i]
;      (println "result:" result)
;      (println "i:" i)
;      (flatten
;        (map
;          (fn [array-element]
;            (println "map. array-element:" array-element)
;            (println "map. result:" result)
;            (println "map. i:" i)
;            (println "map. (seq [result, array-element]):" (seq [array-element]))
;            (println "map. (cons result array-element):" (conj result array-element))
;            (if (= i 0)
;              ;(seq [result, array-element])
;              (seq [array-element])
;              ;(array i)
;              (conj result array-element)))
;          (filter
;            (fn [array-element]
;              (println "filter. array-element:" array-element)
;              (println "filter. result:" result)
;              (println "filter. (last result):" (last result))
;              (println "filter. i:" i)
;              (if (= i 0)
;                ;(= result array-element)
;                true
;                (= (last result) array-element))
;              array-element)
;            array))
;        )
;      )
;    []
;    (range max-length)))
;
;(println "Hello containers!")
;(def v [1 2 3 [1 2 3] 5 "string"])
;(println (v 3))
;(println (v 5))
;(println [1 2 3])
;(println "Hello seqs!")
;(println (seq [1 2 3]))
;(println "Hello lists!")
;(println (list 1 2 3))
;(println "Hello sets!")
;(println (set '(1 2 3)))
;(println "Hello maps!")
;(def m {:1 1 :abc 33 :2 "2" })
;(println (m :abc))



;(println "Hello permutations!")

(defn test-map
  [x, y, i]
  (if (= i 0)
    (seq [x y])
    (cons x y)))

;(println (test-map "A" "B" 0))
;(println (test-map ["A" "B"] "C" 3))

;(println (permutation ["a", "b", "c"] 3))

(defn words [data, n]
  (reduce
    (fn [result, i]
      (flatten
        (map
          (fn [result-element]
            (map
              (fn [data-element]
                (str result-element data-element))
              (filter
                (fn [element]
                  (not=
                    (last result-element)
                    element))
                data)))
          result))
      )
    [""]
    (range n)))

;(println (words ["f" "d" "r"] 3))


(println "END")

;(defn permutation
;  "Function of permutations"
;  [array, max-length]
;  (println array)
;  (println max-length)
;  (reduce
;    (fn [result, i]
;      (println "result:" result)
;      (println "i:" i)
;      (flatten
;        (filter
;          (fn [array-element]
;            (println "result-element:" array-element)
;            (println "result:" result)
;            (println "i:" i)
;            (if (= i 1)
;              (= result array-element)
;              (= (last result) array-element))
;            array-element)
;          result))
;      )
;    array
;    (range max-length)))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


; (require '[hello.core :as c])
; (c/foo 3)

;(ns Task1.WordsCode)

(ns WordsCode)

(defn words [n alp]
  (reduce
    (fn [alp1 i]
      (for [x alp1
            y alp
            :when (not (= (last x) y))]
        (.concat x (str y))))
    [""]
    (range n)))

;(println (words 2 (char-array "abc")))
;
;(deftest tests
;         (is (= `(seq ["ab" abc aca acb bab bac bca bcb cab cac cba cbc])
;                (permutation "abc" 3))))