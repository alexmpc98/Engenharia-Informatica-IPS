;;;;       Programa do apoio ao jogo do galo
;;;;       Apos ter desenvolvido as funcoes pedidas no enunciado
;;;;       utilize a funcao fazer-uma-partida para jogar.

;;;;
;;;; Constantes:
;;;;
(defvar *jogador2* -1)
(defvar *jogador1* 1)


;;;;
;;;; Representacao do problema:
;;;;

;;; Criacao de tabuleiro
;;; ----------------------------------
(defun tabuleiro-inicial (&optional stream)
  "Permite criar o tabuleiro inicial do jogo."
  (cond ((null stream) '((0 0 0)
                         (0 0 0)
                         (0 0 0)))
        (t (read stream))))

(defun tabuleiro-teste (&optional (jogador *jogador1*) &aux (jogador-oposto (trocar-peca jogador)))
  (list (list jogador 0 jogador-oposto)
        (list 0 0 jogador-oposto)
        (list jogador 0 0)
  )
)

(defun tabuleiro-vazio()
  '((0 0 0)(0 0 0)(0 0 0))
)

(defun tabuleiro-cheio-jogador1()
  '((1 1 1) (1 1 1)(1 1 1))
)

(defun tabuleiro-vence-jogador1()
  '((0 0 0)(1 1 1)(1 1 1))
)

(defun tabuleiro-vence-jogador2()
  '((0 0 0)(0 0 0)(-1 -1 -1))
)

;;;;
;;;; Selectores
;;;;

(defun linha (linha tabuleiro)
  (cond ((or (< linha 0) (>= linha (length tabuleiro))) NIL)
        (t (nth linha tabuleiro)))
) 

(defun coluna (coluna tabuleiro)
  (mapcar #'(lambda(linha) 
             (cond ((or (< coluna 0) (>= coluna (length linha))) NIL)
                   (t (nth coluna linha))                   
             ) 
           ) tabuleiro)
)

(defun diagonal (diagonal tabuleiro &optional (linha (1- (length tabuleiro))) &aux (dimensao (1- (length tabuleiro))))
  (cond ((or (< linha 0) (< diagonal 0) (> diagonal 1)) NIL)
        (t (append (diagonal diagonal tabuleiro (1- linha))
                   (list (celula linha (abs (- (* diagonal dimensao) linha)) tabuleiro))))
  )
)

(defun celula (linha coluna tabuleiro)
  (linha linha (coluna coluna tabuleiro))
)


;;;;
;;;; Funcoes auxiliares
;;;; 
(defun substituir-posicao (posicao lista &optional (valor 0) &aux (resto (cdr lista)))
  (cond ((null lista) NIL)
        ((< posicao 0) lista)
        ((= posicao 0) (cons valor resto))
        (t (cons (car lista) (substituir-posicao (1- posicao) resto valor)))
  )
)

(defun colocar-peca (linha coluna tabuleiro &optional (valor 0))
  (substituir-posicao linha tabuleiro (substituir-posicao coluna (linha linha tabuleiro) valor))
)


;;;; 
;;;; Entrada / saida de dados
;;;;

;;; Funcoes para imprimir um tabuleiro
;;; ----------------------------------



;;;;
;;;; Funcoes para o jogo
;;; ----------------------------------
(defun jogada-humano(tabuleiro jogador)
  (imprime-tabuleiro tabuleiro)
  (format t "~%")
  (cond
   ((tabuleiro-preenchidop tabuleiro) (format t "Jogo terminado, tabuleiro cheio!"))
   ((not (null (tabuleiro-solucao tabuleiro))) (format t "Jogo terminado!") nil)
   (t
  (progn
    (format t "~A - Qual � a linha em que deseja inserir a pe�a ~%" jogador)
    (let((linha(read)))
      (cond
       ((or (< linha 0)(> linha 2)) (format t "Linha inserida n�o � valida! ~%")(jogada-humano tabuleiro jogador))
       (t (format t "~A Qual � a coluna em que deseja inserir a pe�a ~%" jogador) (let((coluna(read)))
         (cond
          ((or (< coluna 0)(> coluna 2))(format t "Coluna inserida n�o � valida! ~%")(jogada-humano tabuleiro jogador))
          (t 
            (cond
              ((not (equal (celula linha coluna tabuleiro) 0)) (format t "Posi��o j� se encontra ocupada") (jogada-humano tabuleiro jogador))
              ((and (equal jogador 'jogador1)(not (tabuleiro-preenchidop tabuleiro))) 
                (jogada-humano (colocar-peca linha coluna tabuleiro 1) 'jogador2))
              ((and (equal jogador 'jogador2)(not (tabuleiro-preenchidop tabuleiro)))
                (jogada-humano (colocar-peca linha coluna tabuleiro -1) 'jogador1))
              (t (format t "Tabuleiro j� est� totalmente preenchido!") nil)
             )
          )
         )
        )
       )
      )
    )
  )
 )
)
)


(defun tabuleiro-preenchidop(tabuleiro)
  (cond
    ((find 0 (apply 'append (mapcar #'(lambda(l)(member 0 l)) tabuleiro))) nil)
    (t t)
  )
)


(defun tabuleiro-solucao(tabuleiro &optional (n 0))
  (cond
    ((= n 3) nil)
    (t
  (cond
   ((= (apply '+ (linha n tabuleiro)) 3) (format t "Jogador 1 venceu pela linha ~D ~%" n) t)
   ((= (apply '+ (coluna n tabuleiro)) 3) (format t "Jogador 1 venceu pela coluna ~D ~%" n) t)
   ((= (apply '+ (diagonal n tabuleiro)) 3) (format t "Jogador 1 venceu pela diagonal ~D ~%" n) t)
   (t 
     (cond
       ((= (apply '+ (linha n tabuleiro)) (* 3 -1)) (format t "Jogador 2 venceu pela linha ~D ~%" n) t)
       ((= (apply '+ (coluna n tabuleiro)) (* 3 -1)) (format t "Jogador 2 venceu pela coluna ~D ~%" n) t)
       ((= (apply '+ (diagonal n tabuleiro)) (* 3 -1)) (format t "Jogador 2 venceu pela diagonal ~D ~%" n) t)
       (t (tabuleiro-solucao tabuleiro (+ n 1)))
      )
     )
   )
  )
 )
)


(defun imprime-tabuleiro(tabuleiro)
   (not (null (mapcar #'(lambda(l) (format t "~%~t~t ~a" l)) tabuleiro)))
)


;;;;
;;;; Funcoes de jogo (humano e computador c/minimax)
;;;;
(defun trocar-peca (peca)
  "Troca a peca de um jogador para a peca de outro jogador."
  (- 0 peca)
)





