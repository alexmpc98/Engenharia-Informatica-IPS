;;;; laboratorio2.lisp
;;;; Disciplina de IA - 2021 / 2022
;;;; Ficha de Laboratório nº2
;;;; Autor: 

;;; Para Debug
;; soma-3
(defun soma-3 (a b c)
"Função que faz a soma de 3 números passados como argumento"
	(+ a b c)
)

(defun notas-dos-alunos()
  '((15.5 15 8.25 13) (17.5 11 9 13.25) (11.75 0 0 16))
)

(defun notas-do-primeiro-aluno(list)
  (car list)
)

(defun calcula-media-notas(list)
  (/
   (+ (first list) (second list) (third list) (fourth list))(length list))
)

(defun maior-nota-do-aluno(list)
  (max (first list) (second list) (third list) (fourth list))
)
;;; Exercicios sobre funções em Lisp (não recursivas)
;; notas-dos-alunos
;; notas-do-primeiro-aluno
;; calcula-media-notas
;; maior-nota-do-aluno