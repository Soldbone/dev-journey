# syntax=docker/dockerfile:1
FROM rust:1.81

WORKDIR /usr/src/project

COPY . .
RUN cargo build --release

EXPOSE 7878
ENTRYPOINT ["./target/release/hello-rust"]