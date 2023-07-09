#include<time.h>
#include<string.h>
#include<stdio.h>
int main(int argc,char *argv[]){
    char buf[100];
    time_t tt;
    struct tm* pointer; 
    time(&tt); 


    if(strcmp(argv[1],"-I")==0){
        pointer = localtime(&tt);
		strftime(buf,80,"%Y-%m-%d ", pointer);
		printf("%s\n", buf);
	}

    else if(strcmp(argv[1],"-u")==0){
        pointer = gmtime(&tt); 
        strftime(buf,80,"%a %b %d %T UTC %Y", pointer);
        printf("%s\n",buf); 
    }
}