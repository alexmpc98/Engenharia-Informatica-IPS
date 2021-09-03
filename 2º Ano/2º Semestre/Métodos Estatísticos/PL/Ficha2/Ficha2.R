# **************** Exercicio 1 ****************
# Num dado terreno instalou-se um pluviometro para medir a 
# precipitacao em milimetros (mm). Durante um ano foram 
# obtidos os seguintes totais mensais:

# Questão 1.1 - Identifique e classifique a variavel.
# Variável quantitativa continua.

# Questão 1.2 - Introduza a amostra no R.
df1 <- data.frame(
  Mes = c("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"),
  Chuva = c(101.0, 60.7, 75.1, 19.9, 26.7, 10.5, 2.5, 39.8, 5.7, 51.7, 50.1, 170.6)
)

# Questão 1.3 - Recorrendo aos comandos do R, calcule as seguintes quantidades:
summary(df1)
numSummary(df1[, "Chuva", drop=FALSE], statistics=c("mean", "sd", "IQR", "quantiles"), quantiles=c(0,.25,.5,.75,1))

# (a) a precipitacao total anual.
pretotal<-sum(df1$Chuva)

# (b) a precipitacao mensal media.
premean<-mean(df1$Chuva)

# (c) a precipitacao mensal mediana.
premedian<-median(df1$Chuva)

# (d) a variancia das precipitacoes mensais.
prevar<-var(df1$Chuva)

# (d) a variancia das precipitacoes mensais.
predesviopadrao<-sqrt(var(df1$Chuva))

# (f) a precipitacao mensal minima.
premin<-min(df1$Chuva)

# (g) a precipitacao mensal maxima.
premax<-max(df1$Chuva)

# Questão 1.4 - Recorrendo aos comandos do R, identifique:

# (a) qual o mes onde se verificou a precipitacao minima.
subset(df1, Chuva == min(df1$Chuva), select=Mes)

# (b) qual o mes onde se verificou a precipitacao maxima.
subset(df1, Chuva == max(df1$Chuva), select=Mes)

# (c) quais os meses com precipitacao acima da media.
subset(df1, Chuva > mean(df1$Chuva), select=Mes)

# Questão 1.5 - Recorrendo aos comandos do R, calcule as medidas de localizacao 
# da precipitacao nos meses de Junho a Setembro (inclusive).
newdata <- df1[6:9,]
newdata$Chuva
summary(newdata$Chuva)

# Questão 1.6 - Recorrendo aos comandos do R, identifique os meses com 
# precipitacao superior a 50 mm.
subset(df1, Chuva > 50, select=Mes)

# Questão 1.7 - Recorrendo aos comandos do R, construa a tabela de 
# frequencias e o respetivo histograma considerando a regra de Sturges 
# para definir as classes.
install.packages("fdth")
tf16 <- fdt(df1$Chuva)

nclasses<-trunc(1+log(length(df1$Chuva), base = exp(1))/log(2, base = exp(1)))
nclasses
hist(df1$Chuva, main="with breaks=4")

# Questão 1.8 - Recorrendo aos comandos do R, construa a tabela de frequencias 
# e o respetivo histograma considerando classes com amplitude de 30 mm, 
# com inicio em zero e terminando em 180 mm.
brk<-seq(0, 180, 30); 
classes<-c("0-30", "30-60", "60-90", "90-120", "120-150", "150-180")
table(cut(df1$Chuva, breaks=brk, right=FALSE,labels=classes))
plot(table(cut(df1$Chuva, breaks=brk,right=FALSE,labels=classes)), ylab="Freq.")


# **************** Exercicio 2 ****************
# O programa R disponibiliza alguns conjuntos de dados. 
# Os seus nomes e breves descricoes podem ser consultados 
# atraves do comando "data()". Entre esses dados encontra-se 
# "sunspots", onde se registam o numero medio de manchas 
# solares observadas em cada mes, nos anos entre 1749 e 1983.

# Questão 2.1 - Identifique e classifique a variavel.
# Variável quantitativa continua
data("sunspots")

# Questão 2.2 - Recorrendo aos comandos do R, construa a tabela 
# de frequencias e o respetivo histograma considerando a regra 
# de Sturges para definir as classes.
sunset_tabfreq<-fdt(sunspots)
hist(sunspots)

# Questão 2.3 - Recorrendo aos comandos do R, construa a tabela 
# de frequencias e o respetivo histograma considerando classes 
# com amplitude de 10, com inicio em zero e terminando em 260.
Sun_nclasses<-trunc(1+log(length(sunspots), base = exp(1))/log(2, base = exp(1)))
tf2<-fdt(sunspots, start=0, end=260, h=10)

brk2<-seq(0, 260, 26); # define os intervalos de classe
classes2<-c("0-26", "26-52", "52-78", "78-104", "104-130", "130-156", "156-182", "182-208", "200-234", "234-260")
table(cut(sunspots,breaks=brk2,right=FALSE,labels=classes2))
sunset_tabfreq<-fdt(sunspots)
plot(table(cut(sunspots,breaks=brk2,right=FALSE,labels=classes2)), ylab="Freq.")

# Questão 2.4 - Recorrendo aos comandos do R, calcule:
numSummary(sunspots)

# (a) os extremos dos dado:
min(sunspots)
max(sunspots)

# (b) os quartis dos dados:
quantile(sunspots, type=4)
summary(sunspots)

# (c) o nono decil dos dados:
quantile(sunspots, .9, type=5)

# (d) o terceiro percentil dos dados:
quantile(sunspots, .03, type=4)

# (e) as medidas de localização central:
mean(sunspots)
median(sunspots)

# (f) as medidas de dispersão:
var(sunspots)
sd(sunspots)

# (g) as medidas de simetria e achatamento:
library(DescTools)
install.packages("moments")
library(moments)
skewness(sunspots)
kurtosis(sunspots)

# Questão 2.5 - diagrama de extremos e quartis dos dados.
boxplot(sunspots, main="Boxplot")

