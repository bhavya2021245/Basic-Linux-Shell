#include <stdio.h>

#include <string.h>

#include <fcntl.h>

#include <errno.h>

#include <stdlib.h> 

#include <unistd.h> 

#include <libgen.h> 

#include <sys/shm.h>

#include <sys/types.h>

#include <sys/stat.h>

#include <sys/wait.h>

#include <time.h>

#include <dirent.h>
#include<pthread.h>

void pwd(){

    char chpwd[500];

    getcwd(chpwd,500);

    printf("%s\n",chpwd);

}

void *func(void * arg){
    system(arg);
}

int main(){

    printf("%s","-------------------------------------------");

    printf("\n");

    printf("%s \n","Welcome to this interactive Unix Shell");

    printf("%s","-------------------------------------------");

    printf("\n");

    pwd();

    while(3){

    char input[500];

      fgets(input,500,stdin);

    

    char input_2d[40][400];
    char str1[10000];

    char *tok;

    tok=strtok(input," \n");

    int total_words=0;

    while(tok!=NULL){

        strcpy(input_2d[total_words],tok);

        total_words++;

        tok=strtok(NULL," \n");

    }
    strcpy(str1,"");
    for(int jg=0;jg<total_words-1;jg++){
        strcat(str1,input_2d[jg]);
        strcat(str1," ");
    }
    // printf("%s",str1);
    
    if(strcmp(input_2d[total_words-1],"&t")!=0){
        
        if(strcmp(input_2d[0],"pwd")==0){
        
        if(total_words==1){

            pwd();

        }
    
        else if(total_words==2){
            
             pwd() ;
        }

        else{
            
            printf("This is not an appropiate command");

        }

    }

    else if(strcmp(input_2d[0],"cd")==0){

        if(strcmp(input_2d[1],"~")==0 || total_words==1){

            chdir("/home/bhavya");

        }
        
        else {
            int m = chdir(input_2d[1]);
            if(m) printf("error: %s\n",strerror(errno));
        }

        

    }
    else if(strcmp(input_2d[0],"echo")==0){
        if(strcmp(input_2d[1],"-E")==0){
            int i=2;
            while(i<total_words){
                printf("%s ",input_2d[i]);
                i++;
            }
            printf("\n");
        }
        else if(!strcmp(input_2d[1],"-n")){
            int i=2;
            while(i<total_words){
                printf("%s ",input_2d[i]);
                i++;
            }
            // printf("\n");
        }
        else{
            int i=2;
            while(i<total_words){
                printf("%s ",input_2d[i]);
                i++;
            }
            printf("\n");
        }
        

    }else if(strcmp(input_2d[0],"date")==0){
         if(total_words!=1){
            pid_t p=fork();
            if(p==0){
                execl("/home/bhavya/Desktop/A1/date","/home/bhavya/Desktop/A1/date",input_2d[1],NULL,NULL);
            }else if(p>0){
                wait(NULL);
            }else{
                printf("Error");
            }
        }
        else{
            char buf[100];
            time_t tt;
            struct tm* pointer; 
            time(&tt);
            pointer = localtime(&tt); 
            strftime(buf,80,"%a %b %d %T %Z %Y", pointer);
            printf("%s\n",buf);
        }
    }else if(strcmp(input_2d[0],"cat")==0){
        if(total_words==1){
            printf("Error");
        }
        else if(total_words==2){
            pid_t p=fork();
            if(p==0){
                execl("/home/bhavya/Desktop/A1/cat","/home/bhavya/Desktop/A1/cat",input_2d[1],NULL,NULL);
            }else if(p>0){
                wait(NULL);
            }else{
                printf("Error");
            }
            
        }else if(total_words==3){
            if(strcmp(input_2d[1],"-E")==0){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/cat","-E",input_2d[2],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
            }else if(strcmp(input_2d[1],"-n")==0){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/cat","-n",input_2d[2],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
            }else {
                printf("Error");
            }
        }else{
        printf("Error");
        }


    }
    else if(strcmp(input_2d[0],"mkdir")==0){
        if(total_words==1){
            printf("error: %s\n",strerror(errno));
        }
        else{
            if(strcmp(input_2d[1],"-v")==0){
                for(int i=2;i<total_words;i++){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/mkdir","-v",input_2d[i],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
                }
            }
            else if(strcmp(input_2d[1],"-m")==0){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/mkdir","-m",input_2d[2],input_2d[3],NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }

            }else{
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/mkdir","/home/bhavya/Desktop/A1/mkdir",input_2d[1],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
            }
            
        }


    }
    else if(strcmp(input_2d[0],"ls")==0){
        if(total_words==1){
            pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/ls","/home/bhavya/Desktop/A1/ls",NULL,NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
        }
        else{
            if(strcmp(input_2d[1],"~")==0){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/ls","~",NULL,NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
            }
            else if(strcmp(input_2d[1],"-a")==0){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/ls","-a",NULL,NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
            }
        }
    }
    else if(strcmp(input_2d[0],"rm")==0){
        if(strcmp(input_2d[1],"-i")==0){
            pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/rm","-i",input_2d[2],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }

        }
        else if(strcmp(input_2d[1],"-v")==0){
            for(int i=2;i<total_words;i++){
                pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/rm","-v",input_2d[i],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
            }
        }
        else{
            pid_t p=fork();
                if(p==0){
                    execl("/home/bhavya/Desktop/A1/rm","/home/bhavya/Desktop/A1/rm",input_2d[1],NULL,NULL);
                }else if(p>0){
                    wait(NULL);
                }else{
                    printf("Error");
                }
        }
    }}
    else{
        pthread_t thread;
        pthread_create(&thread,NULL,func,str1);
        pthread_join(thread,NULL);
    }

    }}