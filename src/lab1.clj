(ns lab1)

(defn words [data, n]
  (reduce
    (fn [result, i]
      (apply concat
        (map
          (fn [result-element]
            (map
              (fn [data-element]
                (if (= i 0)
                  (list result-element data-element)
                  (concat result-element data-element)))
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

(println (words (list "a" "b" "c") 3))
;(println (words (list "a" "b" 4) 3))
;(println (words (list "a" 11 false) 3))
; (println (words (list "a" 11 [33 44]) 3))
; (println (words (list "a" 11 [33 44] false) 4))

;(println (list "a"))
;(println (list (list "a")))
;(println (cons "11" (list "a")))

;(println (list (list "a")))
;(println (list (list "c") (list "d")))
;
;(println (cons "e" (list (list "a"))))
;
;(println (apply cons (list (list "a")) (list (list "c") (list "d"))))

; empty list
; mapcat вместо flatten и str