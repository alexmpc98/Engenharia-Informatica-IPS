;;;; laboratorio5.lisp
;;;; Ficha laboratorial sobre funcoes de alto nivel em Lisp
;;;; Autor: 


;;; Exercicio Introdutorio  - funcall + lambda
;;(remover-se #'(lambda (x) (= x 0)) '(1 2 0 2 0 4))
(defun remover-se(pred lista)
  (cond ((null lista) NIL) 
        ((funcall pred (car lista)) (remover-se pred (cdr lista)))
        (T (cons (car lista) (remover-se pred (cdr lista))))))


;;; Exercicios - Parametros de lambda
;;(media 1 2 34 )
;; 12.333333


;; (coluna ((1 2 3) (4 5 6) (7 8 9)))
;; (1 4 7)


;; (aplicar-simbolo 'mod  2 3)
;; 2
;; (aplicar-simbolo '*  2 3)
;; 6


;;; Exercicio avaliacao de turmas de alunos

;; Teste: (turma-1)
;; (("Joao" "Silva" (12.5 15 8.5)) ("Ana" "Santos" (11.5 18 13.5)) ("Paulo" "Jorge" (6.5 10 7.5)) ("Elisabete" "Navarro" (12.5 15 8.5)) ("Mario" "Rodrigues" (12.5 15 8.5)))


;; nome


;; apelido


;; notas

;; media-das-notas
;; Teste: (media-das-notas '(10 15 20))
;; 15


;; media-da-turma
;; (media-da-turma #'media-notas (turma-1))
;; 11.666666


;; percentagem-de-aprovados
;; (percentagem-aprovados (turma-1))
;; 80.0


;; lista-dos-aprovados
;; Teste: (lista-dos-aprovados (turma-1))
;; Teste avaliar-turma: (avaliar-turma 'lista-dos-aprovados (turma-1))
;; (("Joao" "Silva") ("Ana" "Santos") NIL ("Elisabete" "Navarro") ("Mario" "Rodrigues"))


;; todos-aprovadosp
;; (todos-aprovadosp (turma-1))
;; NIL


;;avaliar-turma
;;(("Joao" "Silva" (12.5 15 8.5)) ("Ana" "Santos" (11.5 18 13.5)) NIL ("Elisabete" "Navarro" (12.5 15 8.5)) ("Mario" "Rodrigues" (12.5 15 8.5)))
;; (avaliar-turma (turma-1) 'media-da-turma)
;; 11.6666
;; (avaliar-turma (turma-1) 'percentagem-aprovados )
;; 80.0


;; existep
;; (existep "Joao" "Silva" (turma-1)
;; T


