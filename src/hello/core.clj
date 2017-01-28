; (ns lab1.core
;   (:use clojure.test))

; (defn permutation [a b n]
;   (if (= n 1)
;     a
;   (if (= n 0)
;     nil
;   (let [temp (for [x a 
;                    y b 
;         :when (not (= (first x) y)) 
;            ]
;     (cons y x)
;     )]
;   (if (some #(< (count %) n) temp)
;    (permutation temp b n)
;      temp
;     )
;    )
;   )
;   )
  
; )
; (defn permut [a n]
;   (permutation (map list a) (sequence a) n)
;   )

; (defn main []
;   (let [temp (permut [1 2 3] 1)]
;     (print temp))
;   )

; (deftest tests
  
;   (is (= nil 
;          (lab1.core/permut [1 2 3] 0)))
;   (is (= `((1) (2) (3)) 
;          (lab1.core/permut [1 2 3] 1)))
;   (is (= `((2 1) (3 1) (1 2) (3 2) (1 3) (2 3)) 
;          (lab1.core/permut [1 2 3] 2)))
;   (is (= 12 
;          (count (lab1.core/permut [1 2 3] 3))))
;   (is (every? #(= (count %) 4) (lab1.core/permut [1 2 3] 4))
;       )
;   )
; (tests)  

; (main)
; (permut [1 2 3] 3)




(defn cartesian-product [colls]
  (do
    ; (println (format "colls: %s" (clojure.string/join ", " (map (partial format "{ %s }") (map seq colls)))))
    (if (empty? colls)
      '(())
      (for [x (first colls)
            more (cartesian-product (rest colls))]
        (cons x more)
      )
    )
  )
)
        ; (concat x more)))))

(defn cp [coll1 coll2]
  (do
    (println coll1)
    (for [x coll1]
      (for [y coll2]
        (conj x y)
      )
    )
  )
)

(defn permutation-internal [current alphabet n]
  (do
    ; (println current)
    ; (println alphabet)
    (if (= n 0)
      current
      ; (permutation-internal (cartesian-product [current alphabet]) alphabet (- n 1)))))
      ; (permutation-internal (map (fn [c] cartesian-product [c alphabet]) current) alphabet (- n 1)))))
      (permutation-internal (cp current alphabet) alphabet (- n 1)))))
      ; (map (map () alphabet) current))))

(defn permutation [alphabet number]
  (if (<= number 0) '() (permutation-internal (map list alphabet) alphabet (- number 1))))

(seq (permutation [1 2] 3))
nil
