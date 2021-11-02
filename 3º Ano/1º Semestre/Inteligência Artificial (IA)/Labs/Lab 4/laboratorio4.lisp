;;;; laboratorio4.lisp
;;;; Disciplina de IA - 2021 / 2022
;;;; Laboratorio sobre funcoes recursivas e boas praticas de programacao em Lisp



;;; Exercicio Introdutorio.
;; comprimento de uma lista
(defun comprimento(lista)
"Recebe uma lista e retorna um valor inteiro que indica quantos elementos existem nesta lista"
  (cond
    ((null lista) 0)
    (t (1+ (comprimento  (cdr lista))))
    )
  )
;; tamanho-das-sublistas
(defun tamanho-das-sublistas (lista)
"Recebe uma lista constituida por sublistas e retorna uma lista com valores inteiros que indicadam o comprimento das respetivas sublistas"
  (cond 
    ((null lista) nil)
    (T (cons (comprimento (car lista)) (tamanho-das-sublistas (cdr lista))))
  )
)

;;; Exercicio sobre funcoes recursivas

;; factorial
(defun factorial(n)
  (cond
    ((equal n 0) 1)
    (t (* n (factorial (- n 1))))
  )
)

;; n-esimo
(defun n-esimo(n l)
  (cond
    ((zerop n) (car l))
     (t (n-esimo (- n 1) (cdr l)))
   )
)

;; soma-lista
(defun soma-lista(l)
   (cond
    ((null l) 0)
    (t (+ (car l) (soma-lista(cdr l))))
    )
)

;; existe
(defun existe(n l)
  (cond
    ((null l) nil)
    ((equal n (car l)) l)
    (t (existe n (cdr l)))
   )
)

;; junta
(defun junta(alist blist)
  (cond
    ((null alist) blist)
    ((null blist) alist)
    (t (cons (car alist) (junta (cdr alist) blist)))
  )
)

;; inverte
(defun inverte(l)
  (cond 
    ((null l) nil)
    (t (append (inverte (cdr l)) (list (car l))))
   )
)

;; conta-atomos
(defun conta-atomos(l)
  (cond
    ((null l) 0)
    ((listp (car l)) (+ (conta-atomos (car l)) (conta-atomos(cdr l))))
    (t (+ (conta-atomos(cdr l)) 1))
   ) 
)

;; alisa
(defun alisa(l)
  (cond
    ((null l) nil)
    ((listp (car l)) (append (alisa(car l)) (alisa(cdr l))))
    (t (append (list (car l)) (alisa(cdr l))))
   )
)
