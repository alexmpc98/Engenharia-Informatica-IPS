# Exercicio 1

# Questão 1
seminario <- data.frame(
  tipo = c("engenheiros", "professores", "analistas de dados", "alunos"),
  participantes = c(32, 20, 16, 12)
)

# Questão 2
prop.table(seminario$participantes)*100 # No R, a função prop.table() 
                                        # gera os percentuais para uma tabela
# Install and use DescTools
# install.packages("DescTools")

library(DescTools)

# library(Rcmdr)

Desc(seminario$participantes)

summary(seminario$participantes)


# Exercicio 2

# Questão 1
library(readxl)
obesidade <- read_excel("C:/Users/Sergio/Desktop/obesidade.xlsx")
View(obesidade)

# Questão 2
summary(obesidade)
obesidade <- within(obesidade, {
  FAVC <- factor(FAVC, labels=c("Não", "Sim"))
})

library(readxl)
numSummary(seminario[,"participantes", drop=FALSE], statistics=c("mean", "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))
indexplot(seminario[,'participantes', drop=FALSE], type='h', id.method='y', id.n=2)
