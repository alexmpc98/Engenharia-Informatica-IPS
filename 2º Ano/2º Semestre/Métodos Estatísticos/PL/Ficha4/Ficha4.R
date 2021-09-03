library(Rcmdr)

#Questão 4.1.1
wear <- data.frame( 
  visc = c(1.6, 9.4, 15.5, 20, 22, 35.5, 43, 40.5, 33),
  desg = c(240, 181, 193, 155, 172, 110, 113, 75, 94)
)

# Questão 4.1.2
# Definimos o tamanho(cex) a cor (col) e a forma dos pontos (pch)
plot(wear$visc, wear$desg, main="Viscosidade*Desgaste", xlab="Viscosidade", 
     ylab="Desgaste", col="red", pch=16, cex=1.1)

plot(wear$desg, wear$visc, main="Desgaste*Viscosidade", xlab="Desgaste", 
     ylab="Viscosidade", col="red", pch=16, cex=1.1)

# Questão 4.1.3
# Coeficiente de correlação de pearson
cor1 <- cor(wear$visc, wear$desg)
cor1
cor.test(wear$visc, wear$desg, method = "pearson")

# De acordo com a análise gráfica e com o valor do coeficente de correlação de pearson
# verifica-se uma correlação negativa entre a viscosidade e o desgaste do aço

# Questão 4.1.4
resultado1 <-lm(wear$desg ~ wear$visc)
resultado1
summary(resultado1)

predict(resultado1)
plot(wear$desg, wear$visc)
abline(lm(resultado1))

resultado2 <-lm(wear$visc ~ wear$desg)
resultado2
summary(resultado2)

predict(resultado2)
plot(wear$visc, wear$desg)
abline(lm(resultado2))

# Questão 4.1.5
viscdados<-c(30,75)
previsao<-234.0707-3.509*viscdados
previsao

# Questão 4.1.6
residmodelo<-resid(resultado1)
residmodelo

plot(resid(resultado1) ~ predict(resultado1), pch=16) # Residuos vs. Y esperado
abline(0,0,col="red") # Coloca uma reta no Y = 0

# ---------------------------------------------------------------------------------------------------

# Questão 4.2.1
fabrica_quimica <- read.delim2("D:/GIT_WORKSPACES/Workspace_ME/Ficha4/fabrica_quimica.txt")
View(fabrica_quimica)

# Questão 4.2.2
plot(fabrica_quimica$Temperatura, fabrica_quimica$Vapor)
cor2<-cor(fabrica_quimica$Temperatura,fabrica_quimica$Vapor)
cor2

# Questão 4.2.3
fabrica_quimica$Temperatura[3]<-32

# Questão 4.2.4
plot(fabrica_quimica$Temperatura,fabrica_quimica$Vapor)
cor3<-cor(fabrica_quimica$Temperatura,fabrica_quimica$Vapor)
cor3

# Questão 4.2.5
resultado3<-lm(fabrica_quimica$Vapor ~ fabrica_quimica$Temperatura)
resultado3
summary(resultado3)

# Questão 4.2.6
residuals3<-residuals(resultado3)

# Residuos vs valores previstos
plot(resid(resutaldo3) ~ predict(resultado3),pch=16)
abline(h=0)

# Residuos vs valores da variavel explicativa
plot(fabrica_quimica$Temperatura,residuals(resultado3), xlab="Temperatura", ylab="Residuos")
abline(h=0)

# Questão 4.2.7
# O volume aumenta 920.836

# Questão 4.2.8
tempdados<-c(47,19)
previsao2<-633.550+920.836*tempdados
previsao2

