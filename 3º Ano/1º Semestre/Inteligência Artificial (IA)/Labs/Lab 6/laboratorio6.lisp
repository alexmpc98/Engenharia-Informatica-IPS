;;;; laboratorio6.lisp
;;;; Ficha de Laborat�rio n�6 - O Problema das Vasilhas de �gua
;;;; Autor: 

;;; Inicializa��o do programa
;; iniciar
(defun iniciar ()
"Permite iniciar o programa, fazendo a leitura do teclado do estado inicial e do algoritmo a utilizar para procurar a solu��o (neste caso a procura na profundidade ou na largura)"
  (let* ((no (cria-no (ler-vasilhas)))
         (algoritmo (ler-algoritmo))
         (profundidade (cond ((eql algoritmo 'dfs) (ler-profundidade)) (T 9999))) )
	(cond
		((equal algoritmo 'bfs) (escreve-no (funcall algoritmo no 'no-solucaop 'sucessores (operadores))))
		((equal algoritmo 'dfs) (escreve-no (funcall algoritmo no 'no-solucaop 'sucessores (operadores) profundidade)))
	)
  )
)

;;; Input - interface que permite ler os valores iniciais das vasilhas junto do utilizador.
(defun ler-no-inicial (&optional (f t))
  (read f))

(defun ler-vasilhas ()
"Permite ler do teclado o estado inicial do problema das vasilhas."
  (let ((vasilha-a (ler-vasilha "A")) (vasilha-b (ler-vasilha "B")))
    (list vasilha-a vasilha-b)
    )
)

(defun ler-vasilha (vasilha)
"Permite ler do teclado o valor inicial de uma vasilha.
A fun��o verifica que os valores lidos pertencem ao intervale esperado para cada vasilha."
(progn
    (format t "Insere o valor da vasilha ~A ~%" vasilha)
    (let ((valor (read))
			(a (* valor 2)))
      (cond
        ((AND (equal vasilha "A") (OR (> valor 3) (< valor 0))) (progn (format t "Valor invalido ~%") (ler-vasilha vasilha)))
        ((AND (equal vasilha "B") (OR (> valor 5) (< valor 0))) (progn (format t "Valor invalido ~%") (ler-vasilha vasilha)))
        (T valor)
      )
  )
))

;; ler-algoritmo
(defun ler-algoritmo ()
"Permite fazer a leitura do algoritmo a utilizar."
  (progn
    (format t "Que algoritmo quer usar para procurar? ~%")
    (format t "1- Procura na largura ~%")
    (format t "2- Procura na profundidade ~%")
    (let ((resposta (read)))
      (cond ((= resposta 1) 'bfs)
            (T 'dfs)))
    )
  )
;; ler-profundidade
(defun ler-profundidade()
"Permite fazer a leitura da profundidade limite para o algoritmo dfs."
    (progn
    (format t "Qual a profundidade limite? ~%")
    (read)
    ))


;;; Output - escrita do estado do problema
;;
(defun escrever-no (no &optional (g t))
"Permite escrever um no, por defeito no ecra."
  (format g "~A" no))

 
(defun escreve-no (no)
 "Permite escrever no ecra um no do problema."
  (progn
     (format t "| A: ~a | B: ~a | G: ~a |~%" (vasilha-a-conteudo no) (vasilha-b-conteudo no) (no-profundidade no))
     (format t "Pai: ~a ~%" (no-pai no))
  ))

(defun escreve-lista-nos (lista)
  "Permite escrever no ecra uma lista de nos do problema das vasilhas, e.g. um conjunto de sucessores, a lista de abertos etc."
  (cond
   ((null lista) nil)
   (T (progn (escreve-no (car lista)) (escreve-lista-nos (cdr lista))))))


;;; Problema das vasilhas
;;; variaveis de teste e operadores
(defun no-teste ()
"Define um no teste do problema da vasilhas em que A=2, B=2, profundidade=0 e pai=NIL"
 (list '(2 2) 0 nil))

(defun operadores ()
 "Cria uma lista com todos os operadores do problema das vasilhas."
 (list 'vazar-a 'vazar-b 'encher-a 'encher-b 'transferir-a-b 'transferir-b-a))

;;; Construtor
(defun cria-no (vasilhas &optional (g 0) (pai nil))
  (list vasilhas g pai)
)


;;; Metodos seletores
;; no-estado
;; teste: (no-estado (no-teste))
;; resultado: (2 2)
(defun no-estado(no)
  (car no)
)


;; vasilha-a-conteudo
;; teste: (vasilha-a-conteudo (no-teste))
;; resultado: 2
(defun vasilha-a-conteudo(no)
  (car (car no))
)

;; vasilha-b-conteudo
;; teste: (vasilha-b-conteudo (no-teste))
;; resultado: 2
(defun vasilha-b-conteudo(no)
  (car (reverse (car no)))
)

;; no-profundidade
;; teste: (no-profundidade (no-teste))
;; resultado: 0
(defun no-profundidade(no)
  (car (cdr no))
)

;; no-pai
;; teste: (no-pai (no-teste))
;; resultado: NIL
(defun no-pai(no)
  (car (cdr (cdr no)))
)

;;; Operadores do problema
;; transferir: que permite vazar o conteudo de uma vasilha para outra ou para fora.
;; encher: para encher uma vasilha ate o topo

;; teste: (vazar-a (no-estado (no-teste)))
;; resultado: (0 2)
(defun vazar-a(no)
  (list (- (car no) (car no)) (car (reverse no)))
)

;; teste: (vazar-b (no-estado (no-teste)))
;; resultado: (2 0)
(defun vazar-b(no)
  (list (car no) (- (car (reverse no)) (car (reverse no))))
)

;; teste: (encher-a (no-estado (no-teste)))
;; resultado: (3 2)
;(defun encher-a(no)
;  (cond
;    ((< (car no) 3) (encher-a (list (+ (car no) 1) (car (reverse no)))))
;    (t no)                    
;   )
;)
(defun encher-a(no)
  (cond
    ((= (car no) 3) nil)
    (t (list 3 (car (reverse no))))
)
)

;; teste: (encher-b (no-estado (no-teste)))
;; resultado: (2 5)
;(defun encher-b(no)
;  (cond
;    ((< (car (reverse no)) 5) (encher-b (list (car no) (+ (car (reverse no)) 1))))
;    (t no)
;  )
;)
(defun encher-b(no)
  (cond
     ((= (car (reverse no)) 5) nil)
     (t (list (car no) 5))
   )
)

;; teste: (transferir-a-b (no-estado (no-teste)))
;; resultado: (0 4)
(defun transferir-a-b(no)
  (cond
    ((and (< (car (reverse no)) 5) (equal (zerop (car no)) nil)) (transferir-a-b (list (- (car no) 1) (+ (car (reverse no)) 1))))
    (t no)
   )
)

;; teste: (transferir-b-a (no-estado (no-teste)))
;; resultado: (3 1)
(defun transferir-b-a(no)
  (cond
    ((and (< (car no) 3) (> (car (reverse no)) 0)) (transferir-b-a (list (+ (car no) 1) (- (car (reverse no)) 1))))
    (t no)
   )
)


;;; Funcoes auxiliares da procura
;;; predicado no-solucaop que verifica se um estado e final
;; teste: (no-solucaop (no-teste))
;; resultado: NIL
(defun no-solucaop(no)
  (cond
    ((or (= (car (car no)) 1) (= (car (reverse (car no))) 1)) t)
    (t nil)
  )
)

;;; sucessores
;; teste: (novo-sucessor (no-teste) 'encher-a)
;; resultado: ((3 2) 1 ((2 2) 0 NIL))
;; teste: (novo-sucessor (no-teste) 'transferir-a-b)
;; resultado: ((0 4) 1 ((2 2) 0 NIL))
;; teste: (novo-sucessor (cria-no '(3 5)) 'encher-a)
;; resultado: NIL
(defun novo-sucessor(no op)
  (cond
    ((equal op 'encher-a) 
      (cond
        ((equal (encher-a (car no)) nil) nil)
        (t (list (encher-a (car no)) (+ (no-profundidade no) 1)  no))))
    ((equal op 'encher-b) 
        (cond
         ((equal (encher-b (car no)) nil) nil)
         (t (list (encher-b (car no)) (+ (no-profundidade no) 1)  no))
         ))
    ((equal op 'transferir-a-b) (list (transferir-a-b (car no)) (+ (no-profundidade no) 1) no))
    ((equal op 'transferir-b-a) (list (transferir-b-a (car no)) (+ (no-profundidade no) 1) no))
    ((equal op 'vazar-a) (list (vazar-a (car no)) (+ (no-profundidade no) 1) no))
    ((equal op 'vazar-b) (list (vazar-b (car no)) (+ (no-profundidade no) 1) no))
   ) 
)

;; teste: (sucessores (no-teste) (operadores) 'bfs)
;; resultado: (((0 2) 1 ((2 2) 0 NIL)) ((2 0) 1 ((2 2) 0 NIL)) ((3 2) 1 ((2 2) 0 NIL)) ((2 5) 1 ((2 2) 0 NIL)) ((0 4) 1 ((2 2) 0 NIL)) ((3 1) 1 ((2 2) 0 NIL)))
;; teste: (sucessores (no-teste) (operadores) 'dfs 2)
;; resultado: (((0 2) 1 ((2 2) 0 NIL)) ((2 0) 1 ((2 2) 0 NIL)) ((3 2) 1 ((2 2) 0 NIL)) ((2 5) 1 ((2 2) 0 NIL)) ((0 4) 1 ((2 2) 0 NIL)) ((3 1) 1 ((2 2) 0 NIL)))
(defun sucessores(no oplist algoritmo &optional maxprofundidade)
  (cond
    ((equal algoritmo 'dfs) 
      (cond 
        ((equal maxprofundidade (no-profundidade no)) nil)
        (t 
         ; (cond 
             (list (novo-sucessor no (first oplist)) (novo-sucessor no (second oplist)) (novo-sucessor no (third oplist)) (novo-sucessor no (fourth oplist))
                 (novo-sucessor no (fifth oplist)) (novo-sucessor no (sixth oplist))))
          ;)
      ))
    ((equal algoritmo 'bfs) 
       (list (novo-sucessor no (first oplist)) (novo-sucessor no (second oplist)) (novo-sucessor no (third oplist)) (novo-sucessor no (fourth oplist))
                 (novo-sucessor no (fifth oplist)) (novo-sucessor no (sixth oplist))))
   )
)


;;; Algoritmos
;; procura na largura
;; teste: (bfs (no-teste) 'no-solucaop 'sucessores (operadores) nil nil)
;; resultado: ((3 1) 1 ((2 2) 0 NIL))

;; procura na profundidade
;; teste: (dfs (no-teste) 'no-solucaop 'sucessores (operadores) 10)
;; resultado: ((3 1) 1 ((2 2) 0 NIL))



