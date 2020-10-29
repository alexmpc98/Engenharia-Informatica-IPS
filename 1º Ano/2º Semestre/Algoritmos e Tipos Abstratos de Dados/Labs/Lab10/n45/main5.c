#include "accessManagement.h"
#include <stdio.h>
int menu();
void execute(int op, PtWebAcessManagement webM);

int main(){
    PtWebAcessManagement webM= accessManagementCreate(5);
    int op=-1;
    while(op!=0){
        op= menu();
        execute(op,webM);
    }
    printf(" END !\n ");
    accessManagementDestroy(&webM) ;

}

void execute(int op, PtWebAcessManagement webM){
        switch(op){
            case 1:{
                    char url [100];
                    printf("introduza o url >");
                    scanf("%s",url);
                    accessManagementSave(webM,url);
            }break;
            case 2: accessManagementPrint(webM);
            break;
            case 3: accessManagementClear(webM);
            case 4: {
                        WebAccess web;
                        int error=accessManagementUndo(webM,&web);
                        if(error==ACCESS_ERROR)
                            printf("undo is not avaiable");
                        else
                        {
                            webAccessPrint(web);
                        }
                        

            }break;
            default:break;
            

        }


}
int menu(){
    int op;
    do{
        printf("\nMENU\n");
        printf("1-save\n");
        printf("2-print\n");
        printf("3-clear\n");
        printf("4-undo\n\n");
        printf("0-quit\n");
        printf("\n INTRODUZA A SUA OPÇÃO >>");
        scanf("%d",&op);
    } while(op<0 || op>4);
}