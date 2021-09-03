#  ************************* Questão 2 *************************
# 2.1-) Introduza a tabela no R
library(readxl)
obesidade <- read_excel("D:/GIT_WORKSPACES/Workspace_ME/Ficha1/obesidade.xlsx")
View(obesidade)

# Questão 2.5
# Questão 2.5 a) 
obesidade_feminino<-subset(obesidade, Genero=="Feminino")