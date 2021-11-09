;;;; laboratorio5.lisp
;;;; Ficha laboratorial sobre funcoes de alto nivel em Lisp
;;;; Autor: Alexandre Coelho


;;; Exercicio Introdutorio  - funcall + lambda
;;(remover-se #'(lambda (x) (= x 0)) '(1 2 0 2 0 4))
(defun remover-se(pred lista)
  (cond ((null lista) NIL) 
        ((funcall pred (car lista)) (remover-se pred (cdr lista)))
        (T (cons (car lista) (remover-se pred (cdr lista))))))


;;; Exercicios - Parametros de lambda
;;(media 1 2 34 )
;; 12.333333
(defun media (&rest args)
  (/ (apply '+ args) (length args))
)

;; (coluna ((1 2 3) (4 5 6) (7 8 9)))
;; (1 4 7)
(defun coluna (m1) (mapcar #'(lambda (linha &aux(cabeca (car linha))) cabeca) m1))  

;;(defun coluna (m1) (mapcar #'(lambda (linha) (let ((cabeca (car
;;linha))) cabeca)) m1)

;; (aplicar-simbolo 'mod  2 3)
;; 2
;; (aplicar-simbolo '*  2 3)
;; 6
(defun aplicar-simbolo (op &rest a)
  (eval (cons op a))
)


;;; Exercicio avaliacao de turmas de alunos

;; Teste: (turma-1)
;; (("Joao" "Silva" (12.5 15 8.5)) ("Ana" "Santos" (11.5 18 13.5)) ("Paulo" "Jorge" (6.5 10 7.5)) ("Elisabete" "Navarro" (12.5 15 8.5)) ("Mario" "Rodrigues" (12.5 15 8.5)))
(defun turma-1()
    '(("Joao" "Silva" (12.5 15 8.5)) ("Ana" "Santos" (11.5 18 13.5)) ("Paulo" "Jorge" (6.5 10 7.5)) ("Elisabete" "Navarro" (12.5 15 8.5)) ("Mario" "Rodrigues" (12.5 15 8.5)))
)

;; nome
(defun nome(s)
   (eval (first s))
)


;; apelido
(defun apelido(s)
  (eval (second s))
)


;; notas
(defun notas(s)
  (third s)
)

;; media-das-notas
;; Teste: (media-das-notas '(10 15 20))
;; 15
(defun media-das-notas(l)
  (let ((size (length l))) (/(apply #'+ l) size)) 
)


;; media-da-turma
;; (media-da-turma #'media-notas (turma-1))
;; 11.666666
(defun media-da-turma(f l)
   (let ((size (length l)))
      (/ (apply '+ (mapcar #'(lambda(aluno)(funcall f (notas aluno))) l)) size)
    )
)


;; percentagem-de-aprovados
;; (percentagem-aprovados (turma-1))
;; 80.0
(defun percentagem-aprovados(l)
  (let ((size (length l)))
    (* (/ (apply '+ (mapcar #'(lambda(aluno)(if(<= (media-das-notas (notas aluno)) 9.5) 0 1)) l)) size) 100)
   )
)



;; lista-dos-aprovados
;; Teste: (lista-dos-aprovados (turma-1))
;; Teste avaliar-turma: (avaliar-turma 'lista-dos-aprovados (turma-1))
;; (("Joao" "Silva") ("Ana" "Santos") NIL ("Elisabete" "Navarro") ("Mario" "Rodrigues"))
(defun lista-dos-aprovados(l)
   (mapcar #'(lambda(aluno)(if(>=(media-das-notas(notas aluno))9.5)(append (list (nome aluno))(list (apelido aluno))))) l)
)



;; todos-aprovadosp
;; (todos-aprovadosp (turma-1))
;; NIL
(defun todos-aprovadosp(turma &aux(aprovados (lista-dos-aprovados turma)))
  (eval(cons 'and (mapcar #' (lambda(aluno)(not (null aluno))) aprovados)))
)


;;avaliar-turma
;;(("Joao" "Silva" (12.5 15 8.5)) ("Ana" "Santos" (11.5 18 13.5)) NIL ("Elisabete" "Navarro" (12.5 15 8.5)) ("Mario" "Rodrigues" (12.5 15 8.5)))
;; (avaliar-turma (turma-1) 'media-da-turma)
;; 11.6666
;; (avaliar-turma (turma-1) 'percentagem-aprovados )
;; 80.0
(defun avaliar-turma(l &optional (funcao 'lista-dos-aprovados))
  (funcall funcao l)
)

;; existep
;; (existep "Joao" "Silva" (turma-1)
;; T
(defun existep(n a l)
   (eval (cons 'or (mapcar #'(lambda(aluno)(cond
     ((and (equal n (nome aluno))(equal a (apelido aluno))) t))) l)
  ))
)




