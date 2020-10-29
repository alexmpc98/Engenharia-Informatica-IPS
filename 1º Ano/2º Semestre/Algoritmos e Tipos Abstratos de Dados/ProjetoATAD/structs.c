#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include "structs.h"

/**
 * @brief Metodo para a criação de um objecto de data
 * 
 * @param day Dia para ser colocado no objecto de data
 * @param month Mês para ser colocado no objecto de data
 * @param year Ano para ser colocado no objecto de data
 * @return Date
 */
Date createDate(unsigned int day, unsigned int month, unsigned int year){
    Date newDate; // Criação de um novo objeto de data denominado de newDate
    if(day > 31 || day < 0){
        newDate.day = 0;
    }
    else{
        newDate.day = day; // Atribuição do parâmetro dia ao campo do objeto
    }
    if(month > 12 || month < 0){
        newDate.month = 0;
    }
    else{
        newDate.month = month; // Atribuição do parâmetro mes ao campo do objeto
    }
    newDate.year = year; // Atribuição do parâmetro ano ao campo do objeto
    return newDate; // Retorno do objeto de data (Date) gerado
}


/**
 * @brief Metodo para a criação de um objecto de paciente
 * 
 * @param id Numero de identificação do paciente
 * @param sex Genero do paciente
 * @param birthYear Ano de nascimento do paciente
 * @param country Pais do paciente
 * @param region Região do paciente
 * @param infectionReason Razão de infecção do paciente
 * @param infectedBy Numero de identificação do paciente que causou a infecção ao paciente declarado no objeto
 * @param confirmedDate Data de confirmação de infecção do paciente
 * @param releasedDate Data de libertação da infecção do paciente
 * @param deceasedDate Data de óbito do paciente
 * @param status Estado
 * @return Patient
 */
Patient createPatient(
    long int id,char sex[6],
    int birthYear,char country[40], 
    char region[40],char infectionReason[40],
    long int infectedBy,Date confirmedDate, 
    Date releasedDate,Date deceasedDate, 
    char status[10])
{
    Patient newPatient; // Criação de um novo objeto de paciente denominado de newPatient
    char empty[0];
    empty[0] = '\0'; // String que fica no lugar de campos vazios, caso existam!

    // ************************* id ***************************************
    newPatient.id = id; // Atribuição do id a um objeto de paciente
    // ********************************************************************

    // ************************* sex **************************************
    // Validação se o campo "sexo" está corretamente inserido
    // No caso de um campo do tipo string vir em branco deve ser inicialido com uma string "" de comprimento 0
    if(strlen(sex) == 0){
        strcpy(newPatient.sex,empty);
    }
    else
        strcpy(newPatient.sex,sex);

    // **********************************************************************

    // ************************* birthYear **********************************
    // No caso do birth_year vir a branco (ou nula) deve ser inicializado a 1
    if(birthYear == 0){
        newPatient.birthYear = -1;
    }
    else{
        newPatient.birthYear = birthYear;
    }
    // **********************************************************************

     // ************************* country ***********************************
    // No caso de um campo do tipo string vir em branco deve ser inicialido com uma string "" de comprimento 0
    if(strlen(country) == 0){
    strcpy(newPatient.country,empty);
    }
    else{
        strcpy(newPatient.country,country);
    }
    // **********************************************************************

    // ************************* region *************************************
    // No caso de um campo do tipo string vir em branco deve ser inicialido com uma string "" de comprimento 0
    if(strlen(region) == 0){
        strcpy(newPatient.region,empty);
    } 
    else{
        strcpy(newPatient.region,region);
    }
    // **********************************************************************

    // ************************* infectionReason ****************************
    // No caso de um campo do tipo string vir em branco deve ser inicialido com uma string "" de comprimento 0
    if(strlen(infectionReason) == 0){
        strcpy(newPatient.infectionReason,"unknown");
    }
    else{
        strcpy(newPatient.infectionReason,infectionReason);
    }
    // **********************************************************************

    // ************************* infectedBy *********************************
    // No caso do infectedBY vir a branco (ou nula) deve ser inicializado a 1
    if(infectedBy == 0){
        newPatient.infectedBy = -1;
    }
    else{
        newPatient.infectedBy = infectedBy;
    }
    // **********************************************************************

    // Declaração de objetos de Data
    Date newConfirmedDate;
    Date newReleasedDate;
    Date newDeceasedDate;

    // ************************* confirmedDate ******************************
    // No caso de um campo tipo data vir em branco, a data deve ser inicializada a 0/0/0
    if(confirmedDate.day == 0 || confirmedDate.month == 0 || confirmedDate.year == 0){
        newConfirmedDate.day = 0;
        newConfirmedDate.month = 0;
        newConfirmedDate.year =  0;
    }
    else{
        newConfirmedDate.day = confirmedDate.day;
        newConfirmedDate.month = confirmedDate.month;
        newConfirmedDate.year = confirmedDate.year;
    }
    newPatient.confirmedDate = newConfirmedDate; //Atribuição do objecto de data gerado, ao campo confirmedDate do objeto de paciente
    // **********************************************************************
    
    // ************************* releasedDate ******************************
    // No caso de um campo tipo data vir em branco, a data deve ser inicializada a 0/0/0
    if(releasedDate.day == 0 || releasedDate.month == 0 || releasedDate.year == 0){
        newReleasedDate.day = 0;
        newReleasedDate.month = 0;
        newReleasedDate.year =  0;
    }
    else{
        newReleasedDate.day = releasedDate.day;
        newReleasedDate.month = releasedDate.month;
        newReleasedDate.year = releasedDate.year;
    }
    newPatient.releasedDate = newReleasedDate; //Atribuição do objecto de data gerado, ao campo releasedDate do objeto de paciente
    // **********************************************************************

    // ************************* deceasedDate ******************************
    // No caso de um campo tipo data vir em branco, a data deve ser inicializada a 0/0/0
    if(deceasedDate.day == 0 || deceasedDate.month == 0 || deceasedDate.year == 0){
        newDeceasedDate.day = 0;
        newDeceasedDate.month = 0;
        newDeceasedDate.year =  0;
    }
    // No caso de um campo tipo data vir em branco, a data deve ser inicializada a 0/0/0
    else{
        newDeceasedDate.day = deceasedDate.day;
        newDeceasedDate.month = deceasedDate.month;
        newDeceasedDate.year = deceasedDate.year;
    }
    newPatient.deceasedDate = newDeceasedDate;  //Atribuição do objecto de data gerado, ao campo deceasedDate do objeto de paciente
    // **********************************************************************

    // ************************* status ******************************
    // No caso de um campo do tipo string vir em branco deve ser inicialido com uma string "" de comprimento 0
    if(strlen(status) == 0){
        strcpy(newPatient.status,empty);
    }
    else{
        strcpy(newPatient.status,status);
    }
    // **********************************************************************
    return newPatient; // Retorno do objeto de paciente (Patient) com o seu devido tratamento dos campos
}


/**
 * @brief Metodo para a criação de um objecto de região
 * 
 * @param name Nomenclatura da região
 * @param capital Capital da região
 * @param area Area total da região (em m²)
 * @param population População da Região
 * @return Region
 */

Region createRegion(char name[40],char capital[40], float area,int population){
    Region newRegion; // Criação de um objeto de Region denominado de newRegion

    // Todos os campos tem de ter valores
    if(strlen(name) == 0 || strlen(capital) == 0 || population == 0 || area == 0.0){
        return newRegion;
    }
    else{
        strcpy(newRegion.name,name);
        strcpy(newRegion.capital,capital);
        newRegion.population = population;
        newRegion.area = area;
    }

    return newRegion; //// Retorno do objeto de região (Region) com o seu devido tratamento dos campos
}


/**
 * @brief Metodo para imprimir informação sobre um objecto de data
 * 
 * @param dateToPrint Objecto de data
 */
void printDate(Date dateToPrint){
    printf("Date (dd/mm/yyyy) : %d/%d/%d\n",dateToPrint.day,dateToPrint.month, dateToPrint.year);
}

/**
 * @brief Metodo para imprimir informação sobre um objecto de paciente
 * 
 * @param patientToPrint Objecto de paciente
 */
void printPatient(Patient patientToPrint){
    if(patientToPrint.deceasedDate.day > 0){
        printf(" \n |Patient with id - %ld | \n\n ", patientToPrint.id);
        printf("Identification Number - %ld || Year of Birth - %d || Sex - %s \n Country - %s || Region - %s \n Reason Of Infection - %s || Infected By (id) : %ld \n Confirmation Date - %d/%d/%d ||  Death Date - %d/%d/%d || Status - %s \n\n ",
                patientToPrint.id,patientToPrint.birthYear,patientToPrint.sex,
                patientToPrint.country,patientToPrint.region,
                patientToPrint.infectionReason,patientToPrint.infectedBy,
                patientToPrint.confirmedDate.day,patientToPrint.confirmedDate.month,patientToPrint.confirmedDate.year,
                patientToPrint.deceasedDate.day,patientToPrint.deceasedDate.month,patientToPrint.deceasedDate.year,
                patientToPrint.status);
    }
    else{
        printf(" \n | Patient with id - %ld | \n\n ", patientToPrint.id);
        printf("Identification Number - %ld || Year of Birth - %d || Sex - %s \n Country - %s || Region - %s \n Reason Of Infection - %s || Infected By (id) : %ld \n Confirmation Date  - %d/%d/%d ||  Date of Relief - %d/%d/%d || Status - %s \n\n ",
                patientToPrint.id,patientToPrint.birthYear,patientToPrint.sex,
                patientToPrint.country,patientToPrint.region,
                patientToPrint.infectionReason,patientToPrint.infectedBy,
                patientToPrint.confirmedDate.day,patientToPrint.confirmedDate.month,patientToPrint.confirmedDate.year,
                patientToPrint.releasedDate.day,patientToPrint.releasedDate.month,patientToPrint.releasedDate.year,
                patientToPrint.status);
    }
}

/**
 * @brief Metodo para imprimir informação sobre um objecto de região
 * 
 * @param patientToPrint Objecto de região
 */
void printRegion(Region regionToPrint){
    printf("Region - %s | Capital - %s | Population - %d | Area - %.2f m²\n", 
    regionToPrint.name,regionToPrint.capital, regionToPrint.population, regionToPrint.area);
}
