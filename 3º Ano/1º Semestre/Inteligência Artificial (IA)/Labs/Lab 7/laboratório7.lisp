;;;; laboratorio7.lisp
;;;; Ficha de Laboratório nº7 - Apoio ao 1º projeto
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

;5. verifica-casas-vazias: Função que recebe o tabuleiro e uma lista de pares de índices linha e coluna
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


;6. substituir-posicao: Função que recebe um índice, uma lista e um valor (por default o valor é 1) e
;substitui pelo valor pretendido nessa posição.

(defun substituir-posicao(index list &optional (value 1))
   (cond
     ((= index 0)(cons value (cdr list)))
     (t (cons (car list) (substituir-posicao(- index 1)(cdr list) value)))
    )
)



;7. substituir: Função que recebe dois índices, o tabuleiro e um valor (por default o valor é 1). A função
;deverá retornar o tabuleiro com a célula substituída pelo valor pretendido. Utilize a função
;substituir-posicao definida anteriormente
(defun substituir(posx posy tabuleiro &optional (value 1))
    (cond
      ((= posy 0)(cons (substituir-posicao posx (linha posy (tabuleiro-vazio))) (cdr tabuleiro)))
      (t (cons (car tabuleiro)(substituir posx (- posy 1)(cdr tabuleiro) value)))
    )
)


;8. peca-casas-ocupadas: Função que recebe dois índices e um tipo de peça (peca-a, peca-b, peca-c-1
;ou peca-c-2) e retorna uma lista com os pares de índices correspondentes às posições em que irá ser
;colocada a peça. Para todas as peças vamos assumir que os índices passados como argumento para a
;função serão referentes à casa do canto superior esquerdo. No caso do esse-lado será a primeira casa a
;contar da esquerda o ponto de referência.
(defun peca-casas-ocupadas(posx posy peca)
   (cond
     ((equal peca 'peca-a)(list (list posx posy)))
     ((equal peca 'peca-b)(list (list posx posy) (list posx (+ posy 1))(list (+ posx 1) posy)(list (+ posx 1)(+ posy 1))))
     ((equal peca 'peca-c-1)(list (list posx posy) (list (+ posx 1) posy) (list (+ posx 1) (+ posy 1))(list (+ posx 2)(+ posy 1))))
     ((equal peca 'peca-c-2)(list (list posx posy)(list posx (+ posy 1))(list (- posx 1) (+ posy 1))(list (- posx 1)(+ posy 2))))
     (t nil)
   )
)

;9. peca-a: Função que recebe dois índices e o tabuleiro e coloca um quadrado de 1x1 no tabuleiro. A
;função deverá retornar NIL caso a casa pretendida esteja preenchida ou caso a peça esteja a ser
;colocada fora dos limites do tabuleiro
(defun peca-a(posx posy tabuleiro)
  (cond
   ((and(>= posx 0)(>= posy 0)(< posx 14)(< posy 14))
  (cond
    ((equal (casa-vaziap posx posy tabuleiro) T)(substituir posx posy tabuleiro))
     ;;Funciona com este mapcar, mas adiciona mais 1 lista
     ;;(mapcar #'(lambda(positions)(substituir (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-a)))
    (t nil)
   ))
   (t nil)
  )
)


;10. peca-b: Função que recebe dois índices e o tabuleiro e coloca o quadrado 2x2 no tabuleiro tendo
;como ponto de referência o índice passado como argumento. A função deverá retornar NIL caso
;alguma das casas a serem preenchidas não esteja vazia ou caso a peça esteja a ser colocada fora dos
;limites do tabuleiro
(defun peca-b(posx posy tabuleiro)
  (cond
   ((and(>= posx 0)(>= posy 0)(< posx 14)(< posy 14))
  (cond
    ((equal (check-all posx posy tabuleiro) t) (substitute-all tabuleiro (peca-casas-ocupadas posx posy 'peca-b) (length (peca-casas-ocupadas posx posy 'peca-b))))
     ;;(substituir (car(car(peca-casas-ocupadas posx posy 'peca-b))) (car(car(cdr(peca-casas-ocupadas posx posy 'peca-b)))) tabuleiro))
     ;;Não Funciona com este mapcar, adicionar mais lista - dúvidas
     ;;(mapcar #'(lambda(positions)(peca-a (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-b)))
    (t nil)
   ))
   (t nil)
  )
)


(defun substitute-all(tabuleiro index n) 
   (cond
      ((= n 0) nil)
     ; (t (substituir (car index)(car(cdr index)) tabuleiro)  (substitute-all tabuleiro (cdr index) (- n 2)))
      (t (substitute-all (substituir (car index)(car(cdr index)) tabuleiro) (cdr index) (- n 2)))
    )
)
(defun check-all(posx posy tabuleiro)
   (cond 
     ((= (check-nil(mapcar #'(lambda(positions)(casa-vaziap (car positions)(car(cdr positions)) tabuleiro)) (peca-casas-ocupadas posx posy 'peca-b))) 0) t)
     (t nil)
    )
)

(defun check-nil(l)
  (apply '+ (mapcar(lambda(item)(if(null item) 1 0)) l))
)



