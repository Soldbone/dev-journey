#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUF_SIZE 1024
#define PORT 8888 // 포트 번호는 맘대로 정해도 됩니다 (1024번 이상)

void error_handling(char *message);

int main(int argc, char *argv[])
{
    int serv_sock, clnt_sock;
    struct sockaddr_in serv_adr, clnt_adr;
    socklen_t clnt_adr_sz;
    char message[BUF_SIZE];
    int str_len, i;

    // 1. 소켓 생성 (전화기 구입)
    // PF_INET: IPv4, SOCK_STREAM: TCP
    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    if (serv_sock == -1)
        error_handling("socket() error");

    // 2. 주소 할당 (전화번호 부여)
    memset(&serv_adr, 0, sizeof(serv_adr));
    serv_adr.sin_family = AF_INET;
    serv_adr.sin_addr.s_addr = htonl(INADDR_ANY); // 내 컴퓨터의 모든 IP 허용
    serv_adr.sin_port = htons(PORT);

    if (bind(serv_sock, (struct sockaddr *)&serv_adr, sizeof(serv_adr)) == -1)
        error_handling("bind() error");

    // 3. 연결 대기 (개통 완료)
    if (listen(serv_sock, 5) == -1)
        error_handling("listen() error");
    
    printf("Server started. Waiting for connection...\n");

    clnt_adr_sz = sizeof(clnt_adr);

    // 4. 연결 수락 (수화기 듦) - 여기서 클라이언트가 올 때까지 대기함(Blocking)
    // 중요: clnt_sock은 '연결된 그 손님' 전용 소켓입니다.
    clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_adr, &clnt_adr_sz);
    if (clnt_sock == -1)
        error_handling("accept() error");
    else
        printf("Client connected!\n");

    // 5. 데이터 송수신 (Echo)
    // 클라이언트가 보낸 데이터를 읽어서(read) 그대로 다시 보냄(write)
    while ((str_len = read(clnt_sock, message, BUF_SIZE)) != 0)
    {
        write(clnt_sock, message, str_len); // 받은 만큼 다시 씀
        message[str_len] = 0; // 문자열 끝 표시 (출력용)
        printf("Message from client: %s", message);
    }

    // 6. 연결 종료
    close(clnt_sock);
    close(serv_sock);
    return 0;
}

void error_handling(char *message)
{
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}

