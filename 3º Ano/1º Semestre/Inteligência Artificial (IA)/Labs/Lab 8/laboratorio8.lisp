;;;; laboratorio8.lisp
;;;; Ficha de Laborat�rio n�8 - O Problema das Vasilhas de �gua
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
 (list '(2 2) 0 1 nil))

(defun operadores ()
 "Cria uma lista com todos os operadores do problema das vasilhas."
 (list 'vazar-a 'vazar-b 'encher-a 'encher-b 'transferir-a-b 'transferir-b-a))

;;; Construtor
(defun cria-no (vasilhas &optional (g 0) h (pai nil))
  (list vasilhas g h pai)
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
  (car (cdddr no))
)

;;no-heuristica
;;;teste : (no-heuristica (no-teste))
;;; resultado: 1
(defun no-heuristica(no)
  (car (cddr no))
)

;;; no-custo
;;;teste: (no-custo (no-teste))
;;; resultado 1
(defun no-custo(no)
  (+ (no-heuristica no) (no-profundidade no))
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
(defun novo-sucessor(no op h)
  (cond
    ((equal op 'encher-a) 
      (cond
        ((equal (encher-a (car no)) nil) nil)
        (t (list (encher-a (car no)) (+ (no-profundidade no) 1) h  no))))
    ((equal op 'encher-b) 
        (cond
         ((equal (encher-b (car no)) nil) nil)
         (t (list (encher-b (car no)) (+ (no-profundidade no) 1) h  no))
         ))
    ((equal op 'transferir-a-b) (list (transferir-a-b (car no)) (+ (no-profundidade no) 1) h no))
    ((equal op 'transferir-b-a) (list (transferir-b-a (car no)) (+ (no-profundidade no) 1) h no))
    ((equal op 'vazar-a) (list (vazar-a (car no)) (+ (no-profundidade no) 1) h no))
    ((equal op 'vazar-b) (list (vazar-b (car no)) (+ (no-profundidade no) 1) h no))
   ) 
)

;; teste: (sucessores (no-teste) (operadores) 'bfs)
;; resultado: (((0 2) 1 ((2 2) 0 NIL)) ((2 0) 1 ((2 2) 0 NIL)) ((3 2) 1 ((2 2) 0 NIL)) ((2 5) 1 ((2 2) 0 NIL)) ((0 4) 1 ((2 2) 0 NIL)) ((3 1) 1 ((2 2) 0 NIL)))
;; teste: (sucessores (no-teste) (operadores) 'dfs 2)
;; resultado: (((0 2) 1 ((2 2) 0 NIL)) ((2 0) 1 ((2 2) 0 NIL)) ((3 2) 1 ((2 2) 0 NIL)) ((2 5) 1 ((2 2) 0 NIL)) ((0 4) 1 ((2 2) 0 NIL)) ((3 1) 1 ((2 2) 0 NIL)))
(defun sucessores(no oplist algoritmo fheuristica &optional maxprofundidade)
  (cond ((and (equal algoritmo 'dfs) (= maxprofundidade (no-profundidade no))) NIL)
        (T (mapcar #'(lambda(func) (novo-sucessor no func (funcall fheuristica no))) oplist))
  )
)

;; Ordena��o dos n�s: Definir a fun��o ordenar-nos, que implementa a ordena��o crescente de uma
;; lista de n�s, de acordo com o valor do custo de cada n�.
;(defun ordena-nos(list)
;  (cond
;    ((null list) nil)
;    ((<(no-custo(car list))(no-custo(car(cdr list))))(ordena-nos (cons(cdr list)(car list))))
;    (t (ordena-nos(cdr list)))
;   )
;)
(defun ordenar(list)
   (mapcar #'(lambda(x)(ordenar-teste x (cdr list)))(car list))
)


(defun ordenar-teste(x list)
   (mapcar #'(lambda(l)(if(< x (car l)) 0 1)) (cdr list))
)

(defun ordenar-2(x list)
  (cond
    ((< x (car list)) 0)
    ((> x (car list)) 1)
    (t nil)
   )
)

(defun abertos-bfs (abertos sucessores)
  (append abertos sucessores)
)

(defun abertos-dfs (abertos sucessores)
  (append sucessores abertos)
)

(defun no-existep (no lista)
   (not (null (find (no-estado no) (mapcar #'no-estado lista))))
)


;;; Algoritmos
;; procura na largura
;; teste: (bfs (no-teste) 'no-solucaop 'sucessores (operadores) nil nil)
;; resultado: ((3 1) 1 ((2 2) 0 NIL))
(defun bfs (no-inicial solucaop sucessores operadores &optional abertos fechados)
  (let* (
         (abertos (abertos-bfs abertos 
                       (remove nil (mapcar (lambda(x) (cond ((or (no-existep x fechados) (no-existep x abertos)) NIL) (T x))) (sucessores no-inicial operadores 'bfs)))
                  )
         )
         (fechados (cons no-inicial fechados))
        )
  (cond ((remove nil (mapcar solucaop abertos)) (find-if solucaop abertos))
        (T (bfs (car abertos) solucaop sucessores operadores (cdr abertos) fechados))
  ))
)

;; procura na profundidade
;; teste: (dfs (no-teste) 'no-solucaop 'sucessores (operadores) 10)
;; resultado: ((3 1) 1 ((2 2) 0 NIL))
(defun dfs (no-inicial solucaop sucessores operadores profundidade &optional abertos fechados)
  (let* (
         (abertos (abertos-dfs abertos 
                    (remove nil (mapcar (lambda(x) (cond ((or (no-existep x fechados) (no-existep x abertos)) NIL) (T x))) (sucessores no-inicial operadores 'dfs profundidade)))
                  )
         )
         (fechados (cons no-inicial fechados))
        )
  (cond ((remove nil (mapcar solucaop abertos)) (find-if solucaop abertos))
        ((and (= (no-profundidade no-inicial) profundidade) (null abertos)) NIL)
        (T (dfs (car abertos) solucaop sucessores operadores profundidade (cdr abertos) fechados))
  ))
)


