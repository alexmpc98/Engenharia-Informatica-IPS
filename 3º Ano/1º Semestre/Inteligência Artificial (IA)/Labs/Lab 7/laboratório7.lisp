;;;; laboratorio7.lisp
;;;; Ficha de Laborat�rio n�7 - Apoio ao 1� projeto
;;;; Autor: 


;;; Tabuleiro

;(defun tabuleiro-vazio (&optional (dimensao 14))
;  "Retorna um tabuleiro 14x14 (default) com as casas vazias"
;	(make-list dimensao :initial-element (make-list dimensao :initial-element '0))
;)

(defun tabuleiro-vazio()
 '((0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
   (0 0 0 0 0 0 0 0 0 0 0 0 0 0)
 )
)

;;; Exercicios
(defun linha(index tabuleiro)
   (nth index tabuleiro)
)
;recursive
;(defun coluna(index tabuleiro)
;   (cond
;    ((zerop (length tabuleiro)) nil)
;    (t (cons (nth index (linha index tabuleiro))(coluna index (cdr tabuleiro))))
;   )
;)

(defun coluna(index tabuleiro)
  (mapcar #'(lambda(linha)(nth index linha)) tabuleiro)
)


(defun celula(index1 index2 tabuleiro)
  (nth index2 (nth index1 tabuleiro))
)

(defun casa-vaziap(linha coluna tabuleiro)
   (= (celula linha coluna tabuleiro) 0)
)

;5. verifica-casas-vazias: Fun��o que recebe o tabuleiro e uma lista de pares de �ndices linha e coluna
;e devolve uma lista com T ou NIL caso se encontrem vazias.
;;; Tentativa Recursiva - Not done
;(defun verifica-casas-vazias(tabuleiro list)
;   (cond
;     ((= (length list) 0) (cons (casa-vaziap (car (car list)) (car (cdr (car list))) tabuleiro) (verifica-casas-vazias tabuleiro (cdr list))))
;     (t nil)
;   )
;)


(defun verifica-casas-vazias(tabuleiro list)
  (mapcar #'(lambda(index)(casa-vaziap (car index)(car (cdr index)) tabuleiro)) list)
)


;6. substituir-posicao: Fun��o que recebe um �ndice, uma lista e um valor (por default o valor � 1) e
;substitui pelo valor pretendido nessa posi��o.

(defun substituir-posicao(index list &optional (value 1))
   (cond
     ((= index 0)(cons value (cdr list)))
     (t (cons (car list) (substituir-posicao(- index 1)(cdr list) value)))
    )
)



;7. substituir: Fun��o que recebe dois �ndices, o tabuleiro e um valor (por default o valor � 1). A fun��o
;dever� retornar o tabuleiro com a c�lula substitu�da pelo valor pretendido. Utilize a fun��o
;substituir-posicao definida anteriormente
(defun substituir(posx posy tabuleiro &optional (value 1))
    (cond
      ((= posy 0)(cons (substituir-posicao posx (linha posy tabuleiro)) (cdr tabuleiro)))
      (t (cons (car tabuleiro)(substituir posx (- posy 1)(cdr tabuleiro) value)))
    )
)


;8. peca-casas-ocupadas: Fun��o que recebe dois �ndices e um tipo de pe�a (peca-a, peca-b, peca-c-1
;ou peca-c-2) e retorna uma lista com os pares de �ndices correspondentes �s posi��es em que ir� ser
;colocada a pe�a. Para todas as pe�as vamos assumir que os �ndices passados como argumento para a
;fun��o ser�o referentes � casa do canto superior esquerdo. No caso do esse-lado ser� a primeira casa a
;contar da esquerda o ponto de refer�ncia.
(defun peca-casas-ocupadas(posx posy peca)
   (cond
     ((equal peca 'peca-a)(list (list posx posy)))
     ((equal peca 'peca-b)(list (list posx posy) (list posx (+ posy 1))(list (+ posx 1) posy)(list (+ posx 1)(+ posy 1))))
     ((equal peca 'peca-c-1)(list (list posx posy)(list posx (+ posy 1))(list (- posx 1) (+ posy 1))(list (- posx 1)(+ posy 2))))
     ((equal peca 'peca-c-2)(list (list posx posy) (list (+ posx 1) posy) (list (+ posx 1) (+ posy 1))(list (+ posx 2)(+ posy 1))))
     (t nil)
   )
)

;9. peca-a: Fun��o que recebe dois �ndices e o tabuleiro e coloca um quadrado de 1x1 no tabuleiro. A
;fun��o dever� retornar NIL caso a casa pretendida esteja preenchida ou caso a pe�a esteja a ser
;colocada fora dos limites do tabuleiro
(defun peca-a(posx posy tabuleiro)
  (cond
   ((and(>= posx 0)(>= posy 0)(< posx 14)(< posy 14))
  (cond
    ((equal (casa-vaziap posx posy tabuleiro) T)(substituir (car (cdr (car(peca-casas-ocupadas posx posy 'peca-a)))) (car (car (peca-casas-ocupadas posx posy 'peca-a)))  tabuleiro))
     ;;Funciona com este mapcar, mas adiciona mais 1 lista
     ;;(mapcar #'(lambda(positions)(substituir (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-a)))
    (t nil)
   ))
   (t nil)
  )
)


;10. peca-b: Fun��o que recebe dois �ndices e o tabuleiro e coloca o quadrado 2x2 no tabuleiro tendo
;como ponto de refer�ncia o �ndice passado como argumento. A fun��o dever� retornar NIL caso
;alguma das casas a serem preenchidas n�o esteja vazia ou caso a pe�a esteja a ser colocada fora dos
;limites do tabuleiro
(defun peca-b(posx posy tabuleiro)
  (cond
   ((and(>= posx 0)(>= posy 0)(< posx 13)(< posy 13))
  (cond
    ((equal (check-all posx posy tabuleiro 'peca-b) t)        
     (substituir (car (car (cdddr (peca-casas-ocupadas posx posy 'peca-b))))  (car (cdr (car (cdddr (peca-casas-ocupadas posx posy 'peca-b))))) ;; 4 itera��es 
              (substituir (caar (cddr (peca-casas-ocupadas posx posy 'peca-b)))  (car ( cdr (car (cdr (cdr (peca-casas-ocupadas posx posy 'peca-b)))))) ;; Tabuleiro = Fun��o Anterior
              (substituir (caar (cdr (peca-casas-ocupadas posx posy 'peca-b)))  (car (cdr (car (cdr (peca-casas-ocupadas posx posy 'peca-b))))) 
              (substituir (car(car(peca-casas-ocupadas posx posy 'peca-b))) (car(cdr(car(peca-casas-ocupadas posx posy 'peca-b)))) tabuleiro))))
     
      )
    (t nil)
   ))
   (t nil)
  )
)

;11. peca-c-1: Fun��o que recebe dois �ndices e o tabuleiro e coloca a pe�a esse na posi��o de lado no
;tabuleiro tendo como ponto de refer�ncia o �ndice passado como argumento. A fun��o dever� retornar
;NIL caso alguma das casas a serem preenchidas n�o esteja vazia ou caso a pe�a esteja a ser colocada
;fora dos limites do tabuleiro.
(defun peca-c-1(posx posy tabuleiro)
  (cond
   ((and(>= posx 0)(>= posy 0)(< posx 13)(< posy 12))
  (cond
    ((equal (check-all posx posy tabuleiro 'peca-c-1) t)     
     (substituir (car (cdr (car (cdddr (peca-casas-ocupadas posx posy 'peca-c-1))))) (car (car (cdddr (peca-casas-ocupadas posx posy 'peca-c-1)))) ;; 4 itera��es 
              (substituir (car ( cdr (car (cdr (cdr (peca-casas-ocupadas posx posy 'peca-c-1))))))(caar (cddr (peca-casas-ocupadas posx posy 'peca-c-1))) ;; Tabuleiro = Fun��o Anterior
              (substituir (car (cdr (car (cdr (peca-casas-ocupadas posx posy 'peca-c-1))))) (caar (cdr (peca-casas-ocupadas posx posy 'peca-c-1))) 
              (substituir (car(cdr(car(peca-casas-ocupadas posx posy 'peca-c-1))))(car(car(peca-casas-ocupadas posx posy 'peca-c-1))) tabuleiro))))     
      )
    (t nil)
   ))
   (t nil)
  )
)

;;12. peca-c-2: Fun��o que recebe dois �ndices e o tabuleiro e coloca a pe�a esse na posi��o para cima no
;;tabuleiro tendo como ponto de refer�ncia o �ndice passado como argumento. A fun��o dever� retornar
;;NIL caso alguma das casas a serem preenchidas n�o esteja vazia ou caso a pe�a esteja a ser colocada
;;fora dos limites do tabuleiro.
(defun peca-c-2(posx posy tabuleiro)
  (cond
   ((and(>= posx 0)(>= posy 0)(< posx 12)(< posy 13))
  (cond
    ((equal (check-all posx posy tabuleiro 'peca-c-2) t)     
     (substituir (car (cdr (car (cdddr (peca-casas-ocupadas posx posy 'peca-c-2))))) (car (car (cdddr (peca-casas-ocupadas posx posy 'peca-c-2)))) ;; 4 itera��es 
              (substituir (car ( cdr (car (cdr (cdr (peca-casas-ocupadas posx posy 'peca-c-2))))))(caar (cddr (peca-casas-ocupadas posx posy 'peca-c-2))) ;; Tabuleiro = Fun��o Anterior
              (substituir (car (cdr (car (cdr (peca-casas-ocupadas posx posy 'peca-c-2))))) (caar (cdr (peca-casas-ocupadas posx posy 'peca-c-2))) 
              (substituir (car(cdr(car(peca-casas-ocupadas posx posy 'peca-c-2))))(car(car(peca-casas-ocupadas posx posy 'peca-c-2))) tabuleiro))))     
      )
    (t nil)
   ))
   (t nil)
  )
)




;;; --------------------------- Fun��es Auxiliares ----------------------------------
(defun check-all(posx posy tabuleiro peca)
   (cond
    ((equal peca 'peca-b)
     (cond 
      ((= (check-nil(mapcar #'(lambda(positions)(casa-vaziap (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-b))) 0) t)
      (t nil)
      ))
    ((equal peca 'peca-c-1)
    (cond 
      ((= (check-nil(mapcar #'(lambda(positions)(casa-vaziap (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-c-1))) 0) t)
      (t nil)
      ))
    ((equal peca 'peca-c-2)
     (cond 
      ((= (check-nil(mapcar #'(lambda(positions)(casa-vaziap (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-c-2))) 0) t)
      (t nil)
      ))
    (t nil)
   )
)

(defun check-nil(l)
  (apply '+ (mapcar(lambda(item)(if(null item) 1 0)) l))
)



