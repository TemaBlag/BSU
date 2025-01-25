#include <string>
#include <iostream>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, std::string argv[])
{
    int num = atoi(argv[1]);
    if ((num < 0) || (argc != 2)) {
        printf("Enter correct key next time\n");
        return 1;
    }
    string str = get_string("Input text: ");
    int len = strlen(str);
    for (int i = 0; i < len; i++) {
        if ((str[i] < 'A') || (str[i] > 'z') || (str[i] > 'Z' && str[i] < 'a')) {
            continue;
        }
        else {
            str[i] = (str[i] + num);
        }
    }
    printf("%s", str);
    return 0;
}