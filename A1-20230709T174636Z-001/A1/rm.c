#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <dirent.h>
int main(int argc, char *argv[]){
    if(strcmp(argv[0],"-i")==0){
		printf("Do you want to delete this file(Yes,No)\n");
		char response[10];
		scanf("%s",&response);
		if(strcmp(response,"No")==0){
			printf("File not deleted/n");
		}
		
		else{
			
			DIR *dir =opendir(argv[1]);
			if(dir){
				printf("It is a Directory\n");
				closedir(dir);
			}
			else{
				int result=remove(argv[1]);
				if(result == 0){
					printf("File deleted successfully \n" );
				}
				else{
					printf("Error: unable to delete the file");

				}
			}
		}
	}
    else if(strcmp(argv[0],"-v")==0){
		DIR *dir =opendir(argv[1]);
		if(dir){
			printf("It is a Directory\n");
			closedir(dir);
		}
		else{
			int result = remove(argv[1]);
			if(result == 0){
				printf("File deleted successfully \n" );
			}
			else{
				printf("Error: unable to delete the file");

			}
		}
	}
    
    else{
		DIR *dir =opendir(argv[1]);
		if(dir){
			printf("It is a Directory\n");
			closedir(dir);
		}
		else{
			int result = remove(argv[1]);
			if(result == 0){
				printf("File deleted successfully \n" );
			}
			else{
				printf("Error: unable to delete the file");

			}
		}
	}
}
