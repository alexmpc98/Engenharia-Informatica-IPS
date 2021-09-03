# Questão 1
# 1.1-) Introduza a amostra no R
seminario <- data.frame(tipo = c("engenheiros","professores","analistas de dados","alunos"), 
                        participantes = c(32,20,16,12))

# 1.2-) Recorrendo ao comandos do R, organize os dados numa tabela de frequência,
# represente-os graficamente e calcule as medidas adequadas
library(DescTools)
Desc(seminario$participantes)
summary(seminario$participantes)

seminario$frequencia<-prop.table(seminario$participantes)*100 #No R, a função prop.table() gera os percentuais para uma tabela

pie(seminario$frequencia, labels=seminario$tipo)

# Questão 2
# 2.1-) Introduza a tabela no R
library(readxl)
obesidade <- read_excel("D:/GIT_WORKSPACES/Workspace_ME/Ficha1/obesidade.xlsx")
View(obesidade)

# 2.2-) Classifique as variáveis 
summary(obesidade)
obesidade <- within(obesidade, {
  FAVC <- factor(FAVC,labels=c('Não','Sim'))
})
obesidade <- within(obesidade, {
  CH2O <- factor(CH2O, labels=c('Menos de 1 litro','Entre 1 e 2 litros',
                               'Mais de 2 litros'))
})
obesidade <- within(obesidade, {
  Fumar <- factor(Fumar, labels=c('Não','Sim'))
})
obesidade <- within(obesidade, {
  FCVC <- factor(FCVC, labels=c('Nunca','Às vezes','Sempre'))
})
obesidade <- within(obesidade, {
  FAF <- factor(FAF, labels=c('Não pratica','1 ou dois dias','2 ou 4 dias',
                              '4 ou 5 dias'))
})
obesidade <- within(obesidade, {
  CALC <- factor(CALC, labels=c('Às vezes','Frequentemente','Nunca',
                              'Sempre'))
})
obesidade <- within(obesidade, {
  CAEC <- factor(CAEC, labels=c('Às vezes','Frequentemente','Não','Sempre'))
})

# 2.3-) Considere apenas duas variáveis qualitativas nominais. Recorrendo aos comandos do R, organize os dados
# em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas.

#Load das DescTools, caso não esteja loaded
library(DescTools)

# FAVC - Se come alimentos calóricos habitualmente
Desc(obesidade$FAVC)
summary(obesidade$FAVC)
numSummary(obesidade[,"FAVC", drop=FALSE], statistics=c("mean", "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

# Fumar - Se fuma
Desc(obesidade$Fumar)
summary(obesidade$Fumar)
numSummary(obesidade[,"Fumar", drop=FALSE], statistics=c("mean", "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

#2.4-) Considere apenas duas variáveis qualitativas ordinais. Recorrendo aos comandos do R, organize os dados
# em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas

# FCVC - Se come habitualmente vegetais nas refeições
Desc(obesidade$FCVC)
summary(obesidade$FCVC)
numSummary(obesidade[,"FCVC", drop=FALSE], statistics=c("mean", 
                                                         "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

# FAF - Com que frequência pratica atividade física por semana
Desc(obesidade$FAF)
summary(obesidade$FAF)
numSummary(obesidade[,"FAF", drop=FALSE], statistics=c("mean", 
                                                        "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

#2.5-) Recorrendo aos comandos do R, considere apenas as pessoas do género Feminino:
Female <- subset(obesidade, Genero != 'Masculino')

# a-) Considere apenas duas variáveis qualitativas nominais. Recorrendo aos comandos do R, organize os dados

# em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas

# FAVC - Se come alimentos calóricos habitualmente
Desc(Female$FAVC)
summary(Female$FAVC)
numSummary(Female[,"FAVC", drop=FALSE], statistics=c("mean", 
                                                        "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

# Fumar - Se fuma
Desc(Female$Fumar)
summary(Female$Fumar)
numSummary(Female[,"Fumar", drop=FALSE], statistics=c("mean", 
                                                         "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

# b-) Considere apenas duas variáveis qualitativas ordinais. Recorrendo aos comandos do R,
# organize os dados em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas
# FCVC - Se come habitualmente vegetais nas refeições
Desc(Female$FCVC)
summary(Female$FCVC)
numSummary(Female[,"FCVC", drop=FALSE], statistics=c("mean", 
                                                        "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

# FAF - Com que frequência pratica atividade física por semana
Desc(Female$FAF)
summary(Female$FAF)
numSummary(Female[,"FAF", drop=FALSE], statistics=c("mean", 
                                                       "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))





