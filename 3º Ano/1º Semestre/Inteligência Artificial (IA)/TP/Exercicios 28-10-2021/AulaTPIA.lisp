(defun conjunto(l)
  (cond
     ((null l) nil)
     ((member (car  l) (cdr l)) (conjunto(cdr l)))
     (t (cons ( car l) (conjunto (cdr l))))
   )
)

;(defun intersecao(c1 c2)
;  (cond
;   ((null c1) nil)
;   ((null c2) nil)
;   ((member (car c1) c2) (intersecao((cdr c1) c2)))
;   ((member (car c2) c1) (intersecao( c1 (cdr c2))))
;   (t (cons (cons (car c1) (car c2)) (intersecao ((cdr c1) (cdr c2)))))
;   )
;)


(defun soma-dois(list)
  (+ list 2)
)

(defun sum-square-root(list)
  (cond
    ((null list) 0)
    (t (+ (sqrt (car list)) (sum-square-root (cdr list))))
   )
)

(defun count-odds(list)
 (cond
  ((null list) 0)
  ((oddp (car list)) (+ (count-odds (cdr list)) 1))
  (t (count-odds (cdr list)))
  )
)
