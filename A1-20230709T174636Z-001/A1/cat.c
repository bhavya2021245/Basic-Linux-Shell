#include<stdio.h>
#include <stdlib.h>
int main(int argc,char *argv[]){
    

    FILE *pp;
    if (argc==1){
        printf("ERROR-Enter a file name\n");
    }
    if(strcmp(argv[0],"-n")==0){
        int line=1;
        pp=fopen(argv[1],"r");
        if(pp==NULL){
            perror("Error: ");
        }
        else{
           char copy_file[100];
            while (fgets(copy_file, 100,pp) != NULL) {
                  
		        printf("%d %s",line,copy_file); 
                line++;
        }
    }}
    else if(strcmp(argv[0],"-E")==0){
        pp =fopen(argv[1],"r");
		if( pp== NULL){
			perror("Error: ");
		}
		else{
			char copy_file[100];
            while (fgets(copy_file, 100,pp) != NULL) {
                if(copy_file[strlen(copy_file)-1]!='\n'){
                    printf("%s",copy_file);
                }
                else{
                    copy_file[strlen(copy_file)-1]='/';
                    printf("%s\n",copy_file);
                }
		        
		}
    }}
    else{
        pp =fopen(argv[1],"r");
		if( pp == NULL){
			perror("Error: ");
		}
		else{
			char copy_file[100];
            while (fgets(copy_file, 100,pp) != NULL) {

		        printf("%s",copy_file);
		}
	}
	
    }}
void internal_function(FILE *p){
    
    char * buff=NULL;
	size_t l=0;
	ssize_t read;
	while ((read= getline(&buff,&l,p)) != -1){
				printf("%s ",buff );
			}
			fclose(p);
}  
  
    
