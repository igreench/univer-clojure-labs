(ns lab1)

(defn words [data, n]
  (reduce
    (fn [result, i]
      (flatten
        (map
          (fn [result-element]
            (map
              (fn [data-element]
                (println "WHO:")
                (println result-element)
                (println data-element)
                (if (= i 0)
                  (list data-element)
                  (cons result-element data-element))
                ;(str result-element data-element)
                )
              (filter
                (fn [element]
                  (println "WHAT:")
                  (println result-element)
                  (println element)
                  ;(not=
                  ;  (last result-element)
                  ;  element)

                  (if (= i 0)
                    true
                    (not=
                      (last result-element)
                      element))
                  )
                data)))
          result))
      )
    (list nil)
    (range n)))

(println (words (list "a" 1 [3 4]) 3))

; empty list
; mapcat вместо flatten и str