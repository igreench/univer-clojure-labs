(ns lab4)

(defn constant [expr]
  {:pre [(or (= expr true) (= expr false))]}
  (list :const expr))

(defn constant? [expr]
  (= (first expr) :const))

(defn constant-value [expr]
  (second expr))

(defn variable [name]
  {:pre [(keyword? name)]}
  (list :var name))

(defn variable? [expr]
  (= (first expr) :var))

(defn variable-name [expr]
  (second expr))

(defn same-variables? [v1 v2]
  (and
    (variable? v1)
    (variable? v2)
    (= (variable-name v1)
       (variable-name v2))))

(defn _not [& expr]
  (cons :not expr))

(defn _or [& expr]
  (cons :or expr))

(defn _and [& expr]
  (cons :and expr))

(defn _imp [& expr]
  (cons :imp expr))

(defn not? [expr]
  (= (first expr) :not))

(defn or? [expr]
  (= (first expr) :or))

(defn and? [expr]
  (= (first expr) :and))

(defn imp? [expr]
  (= (first expr) :imp))

(defn args [expr]
  (rest expr))

(defn _disc-imp [expr]
  (if (not (imp? expr))
    expr
    (let [x (first (args expr))
          y (second (args expr))]
         (_or (_not x) y)
    )
  )
)

(defn _disc-not [expr]
  (if
    (and (not? expr) (not? (first (args expr))))
    (first (args (first (args expr))))
    expr
  )
)

(defn _de-morg [expr]
  (if (not? expr)
    (let [nest_expr (first (args expr))]
      (cond
        (and? nest_expr)
           (apply _or (map #(_not %) (args nest_expr)))
        (or? nest_expr)
           (apply _and (map #(_not %) (args nest_expr)))
        :else
           expr))
    expr)
  )

(defn _distrib [expr]
(cond (and? expr)
          (case (count (args expr))
                1 (first (args expr))
                2 (let [first (first (args expr))
                        second (second (args expr))]
                  (cond 
                     (or? first)
                        (apply _or (map #(_and % second) (args first)))
                     (or? second)
                        (apply _or (map #(_and first %) (args second)))
                     :else
                         expr
                   ))
                (_and (first (args expr)) (apply _and (rest (args expr))))
                )
          :else
          expr)
)

(defn map-args [f expr]
  (cond (or (variable? expr) (constant? expr))
        expr
    :else
        (f (cons (first expr) (map (partial map-args f) (args expr))))
        )
  )

(defn map-args-subst [f expr]
  (cond (or (variable? expr) (constant? expr))
        (f expr)
    :else
        (f (cons (first expr) (map (partial map-args-subst f) (args expr))))
        )
  )

(defn _reduce-nested [expr]
  ;(println expr)
  (cond (and? expr)
           (apply _and (concat (remove and? (args expr)) (apply concat (map args (filter and? (args expr)))) )) 
        (or? expr)
           (apply _or (concat (remove or? (args expr)) (apply concat (map args (filter or? (args expr)))) ))
       :else 
           expr
        )
  )

(defn reduce-nested [expr]
      (map-args _reduce-nested expr)
  )

(defn distrib [expr]
      (map-args _distrib expr)
  )

(defn disc-imp [expr]
      (map-args _disc-imp expr)
  )

(defn disc-not [expr]
       (map-args _disc-not expr))

(defn de-morg [expr]
       (map-args _de-morg expr))

(defn _subst [var val expr]
  (cond (= expr var) 
        val
    :else
        expr)
  )

(defn subst [var val expr]
  (map-args-subst #(_subst var val %) expr)
  )

(defn _solve [expr]
  (let [temp (args expr)] 
  (cond
    (and (and? expr) (some #(= (constant false) %) temp))
        (constant false)
    (and (and? expr) (every? #(= (constant true) %) temp))
        (constant true)
    (and (or? expr) (some #(= (constant true) %) temp))
        (constant true)
    (and (or? expr) (every? #(= (constant false) %) temp))
        (constant false)
    (and (not? expr) (= (constant false) (first temp)))
        (constant true)
    (and (not? expr) (= (constant true) (first temp)))
        (constant false)
    :else
        expr
    )
  )
 )

(defn solve [expr]
  (map-args _solve expr)
  )

(defn run-full [expr prev-expr]
  (let [new-expr (reduce-nested (distrib (distrib  
    (disc-not 
      (de-morg 
        (disc-imp expr))))))]
    (if (not (= new-expr prev-expr))
          (run-full new-expr new-expr)
          new-expr
      )
    )
  )


(defn run [expr]
      (run-full expr expr)
  )

(defn solve-subst [expr]
   (let [temp 
          (subst (variable :b) (constant true) 
                 (subst (variable :c) (constant false)
                        (subst (variable :a) (constant true)
                              (subst (variable :x) (constant true)
                                    (subst (variable :y) (constant true)
                                           (subst (variable :z) (constant false)
                                                 (subst (variable :e) (constant true) expr)))))))]
      (solve temp)
    )
  )

(defn main[]
    ;(printStep (_and (_or (variable :a) (variable :b) (variable :c)) (_or (variable :d) (variable :e) (variable :f)) (_or (variable :g) (variable :h) (variable :i))))
    ;(run (_imp (variable :b) (_not (_or (variable :a) (_not (_and (variable :c) (_or (variable :a) (variable :c)) (_not (_imp (variable :b) (variable :c)))))))))
    (let [new-exp(run (_imp (_and (variable :a) (_not (_or (_imp (variable :a)(variable :b))(variable :c)))) (_and (variable :c) (variable :c) (variable :a))))]
    (solve-subst new-exp))
    ;(solve-subst (run (_and (_or (variable :a) (variable :b) (variable :c)) (_or (variable :b) (variable :c) (variable :a))) ))
    
    ;(println (_and (_or (variable :a) (variable :b) (variable :c)) (_or (variable :d) (variable :e) (variable :f)) (_or (variable :g) (variable :h) (variable :i))))
    ;(solve-subst (run (_and (_or (variable :x) (variable :e)) (_or (variable :y) (variable :z)) (_or (variable :a) (variable :b))) ))
    ;(println (_and (_or (variable :x) (variable :e)) (_or (variable :y) (variable :z)) (_or (variable :a) (variable :b))))
  )

(defn printStep[expr]
    (println "start: " expr)
    (println "imp: " (disc-imp expr))
    (println "morg: " (de-morg (disc-imp expr)))
    (println "not: " (disc-not (de-morg (disc-imp expr))))
    (println "distrib: "  (distrib (disc-not (de-morg (disc-imp expr)))))
    (println "nest: "   (reduce-nested (distrib (disc-not (de-morg (disc-imp expr))))))
    
    (println "imp: " (disc-imp (reduce-nested (distrib (disc-not (de-morg (disc-imp expr)))))))
    (println "morg: " (de-morg (disc-imp (reduce-nested (distrib (disc-not (de-morg (disc-imp expr))))))))
    (println "not: " (disc-not (de-morg (disc-imp (reduce-nested (distrib (disc-not (de-morg (disc-imp expr)))))))))
    (println "distrib: "  (distrib (disc-not (de-morg (disc-imp (reduce-nested (distrib (disc-not (de-morg (disc-imp expr))))))))))
    (println "nest: "  (reduce-nested (distrib (disc-not (de-morg (disc-imp (reduce-nested (distrib (disc-not (de-morg (disc-imp expr)))))))))))
  )


(main)
