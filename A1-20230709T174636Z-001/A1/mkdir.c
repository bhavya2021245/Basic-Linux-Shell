#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <dirent.h>
int main(int argc, char const *argv[]){
    if(strcmp(argv[0],"-m")==0){
		int mode =atoi(argv[1]);

		DIR* dir =opendir(argv[2]);
		if(dir){
			printf("Directory ");
            printf("already ");
			closedir(dir);
            printf("exit\n");
		}
		else{
			mkdir((const char *)argv[2], mode);
			printf("Directory has been created \n" );
		}
	}
    else if(strcmp(argv[0],"-v")==0){
        DIR* dir=opendir(argv[1]);
        if(dir){
            printf("Directory ");
            printf("already ");
			closedir(dir);
            printf("exit\n");
        }
        else{
            mkdir((const char *)argv[1], S_IRWXU);
            printf("Directory has been created\n");
        }
    }

    else{
        DIR* dir =opendir(argv[1]);
		if(dir){
			printf("Directory ");
            printf("already ");
			closedir(dir);
            printf("exit\n");
		}
		else{
			mkdir((const char *)argv[1], S_IRWXU);
		}
    }
}