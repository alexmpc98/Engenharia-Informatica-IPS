# Questão 1
# 1.1-) Introduza a amostra no R
seminario <- data.frame(tipo = c("engenheiros","professores","analistas de dados","alunos"), 
                        participantes = c(32,20,16,12))

# 1.2-) Recorrendo ao comandos do R, organize os dados numa tabela de frequência,
# represente-os graficamente e calcule as medidas adequadas
library(DescTools)
Desc(seminario$participantes)
summary(seminario$participantes)
seminario$frequencia<-prop.table(seminario$participantes)*100 
#No R, a função prop.table() gera os percentuais para uma tabela
#pie(seminario$participantes, labels = seminario$tipo) - igual ao comando abaixo
pie(seminario$frequencia, labels=seminario$tipo)


# Questão 2
# 2.1-) Introduza a tabela no R
library(readxl)
obesidade <- read_excel("C:/Users/Alexandre/Downloads/obesidade.xlsx")
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
favc.tb <- table(obesidade$FAVC)
prop.table(favc.tb)

Barplot(obesidade$FAVC, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(obesidade$FAVC, xlab="",ylab="", main="Gráfico", scale="percent")

#Obter as medidas
mean(as.numeric(obesidade$FAVC))
median(as.numeric(obesidade$FAVC))
levels(obesidade$FAVC)[median(as.numeric(obesidade$FAVC))]



# Fumar - Se fuma
Desc(obesidade$Fumar)
summary(obesidade$Fumar)
fumar.tb <- table(obesidade$Fumar)
prop.table(fumar.tb)


Barplot(obesidade$Fumar, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(obesidade$Fumar, xlab="",ylab="", main="Gráfico", scale="percent")

#Obter as medidas
mean(as.numeric(obesidade$Fumar))
median(as.numeric(obesidade$Fumar))
levels(obesidade$Fumar)[median(as.numeric(obesidade$Fumar))]


#2.4-) Considere apenas duas variáveis qualitativas ordinais. Recorrendo aos comandos do R, organize os dados
# em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas

# FCVC - Se come habitualmente vegetais nas refeições
Desc(obesidade$FCVC)
summary(obesidade$FCVC)
fcvc.tb <- table(obesidade$FCVC)
prop.table(fcvc.tb)

Barplot(obesidade$FCVC, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(obesidade$FCVC, xlab="",ylab="", main="Gráfico", scale="percent")

#Obter as medidas
mean(as.numeric(obesidade$FCVC))
median(as.numeric(obesidade$FCVC))
levels(obesidade$FCVC)[median(as.numeric(obesidade$FCVC))]

# FAF - Com que frequência pratica atividade física por semana
Desc(obesidade$FAF)
summary(obesidade$FAF)
faf.tb <- table(obesidade$FAF)
prop.table(faf.tb)

Barplot(obesidade$FAF, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(obesidade$FAF, xlab="",ylab="", main="Gráfico", scale="percent")

mean(as.numeric(obesidade$FAF))
median(as.numeric(obesidade$FAF))
levels(obesidade$FAF)[median(as.numeric(obesidade$FAF))]


#2.5-) Recorrendo aos comandos do R, considere apenas as pessoas do género Feminino:
Female <- subset(obesidade, Genero != 'Masculino')

# a-) Considere apenas duas variáveis qualitativas nominais. Recorrendo aos comandos do R, organize os dados

# em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas

# FAVC - Se come alimentos calóricos habitualmente
Desc(Female$FAVC)
summary(Female$FAVC)
favcf.tb <- table(Female$FAVC)
prop.table(favcf.tb)

Barplot(Female$FAVC, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(Female$FAVC, xlab="",ylab="", main="Gráfico", scale="percent")

#Obter as medidas
mean(as.numeric(Female$FAVC))
median(as.numeric(Female$FAVC))
levels(Female$FAVC)[median(as.numeric(Female$FAVC))]


# Fumar - Se fuma
Desc(Female$Fumar)
summary(Female$Fumar)
fumarf.tb <- table(Female$Fumar)
prop.table(fumarf.tb)


Barplot(Female$Fumar, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(Female$Fumar, xlab="",ylab="", main="Gráfico", scale="percent")

#Obter as medidas
mean(as.numeric(Female$Fumar))
median(as.numeric(Female$Fumar))
levels(Female$Fumar)[median(as.numeric(Female$Fumar))]

# b-) Considere apenas duas variáveis qualitativas ordinais. Recorrendo aos comandos do R,
# organize os dados em tabelas de frequências, represente-os graficamente e calcule as medidas adequadas
# FCVC - Se come habitualmente vegetais nas refeições
Desc(Female$FCVC)
summary(Female$FCVC)
fcvcf.tb <- table(Female$FCVC)
prop.table(fcvcf.tb)

Barplot(Female$FCVC, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(Female$FCVC, xlab="",ylab="", main="Gráfico", scale="percent")

#Obter as medidas
mean(as.numeric(Female$FCVC))
median(as.numeric(Female$FCVC))
levels(Female$FCVC)[median(as.numeric(Female$FCVC))]

# FAF - Com que frequência pratica atividade física por semana
Desc(Female$FAF)
summary(Female$FAF)
faff.tb <- table(obesidade$FAF)
prop.table(faff.tb)

Barplot(Female$FAF, xlab="CALC", ylab="Frequency", label.bars=TRUE)
piechart(Female$FAF, xlab="",ylab="", main="Gráfico", scale="percent")

mean(as.numeric(Female$FAF))
median(as.numeric(Female$FAF))
levels(Female$FAF)[median(as.numeric(Female$FAF))]






