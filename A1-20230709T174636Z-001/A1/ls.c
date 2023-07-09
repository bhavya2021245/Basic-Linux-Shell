#include<stdio.h>

#include<string.h>

#include<stdlib.h>

#include<unistd.h>

#include<dirent.h>

#include <err.h>

#include<sys/stat.h>

#include<sysexits.h>
int main(int argc,char *argv[]){
    
    if(strcmp(argv[0],"~")==0){
        struct dirent *strct;
        DIR *dirct=opendir("/home/bhavya");
        while ((strct = readdir(dirct)) != NULL){

            if(strcmp(strct->d_name,"..")!=0){
                if(strcmp(strct->d_name,".")!=0){
                    if(strct->d_name[0]!='.'){
                printf("%s ", strct->d_name);}}

            }

        }
        printf("\n");
    }
    else if(strcmp(argv[0],"-a")==0){
        struct dirent *strct;
        DIR *dirct=opendir(".");
        while ((strct = readdir(dirct)) != NULL){

            if(strcmp(strct->d_name,"..")!=0){
                if(strcmp(strct->d_name,".")!=0){
                    printf("%s ", strct->d_name);}

            }

        }
        printf("\n");
    }
    else{
        struct dirent *strct;
        DIR *dirct=opendir(".");
        while ((strct = readdir(dirct)) != NULL){

            if(strcmp(strct->d_name,"..")!=0){
                if(strcmp(strct->d_name,".")!=0){
                    if(strct->d_name[0]!='.'){
                printf("%s ", strct->d_name);}}

            }

        }
        printf("\n");
    }
}