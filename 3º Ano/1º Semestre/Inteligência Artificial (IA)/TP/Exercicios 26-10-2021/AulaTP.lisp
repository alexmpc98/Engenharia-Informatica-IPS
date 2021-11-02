(defun fresolvente(a b c)
  (list (/ (- (* b -1) (sqrt (- (* b b) (* 4 a c)))) (* 2 a)) (/ (+ (* b -1) (sqrt (- (* b b) (* 4 a c)))) (* 2 a)) )
)


(defun maximo(list)
  (cond
     ((null list) nil)
     ((null (cdr list)) (car list))
     ((< (first list) (second list)) (maximo(cdr list)))
     (t (maximo(cons (car list) (cddr list))))
   )
)

(defun fib(n)
  (cond
    ((eq n 0) 1)
    ((eq n 1) 2)
    (t (+ (fib (- n 1)) (fib (- n 2)))) 
  )
)

(defun binario(n)
  (cond 
   ((< n 1) nil)
   ((= n 1) '(1))
   ((zerop (mod n 2)) (append (binario (/ n 2)) '(0)))
   (t (append (binario (floor (/ n 2))) '(1)))) 
)

;;(defun hexadecimal(n)
;;  (cond
;;    ((zerop (/ n 16)) nil)
;;    (t (append (hexadecimal(/ n 16)) (list (mod n 16))))
;;   )
;;)

;; not working





