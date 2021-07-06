# golang-practice

## 01-getting-started

```go
go mod init example.com/hello
go mod tidy
go run .
```

### Links

- https://golang.org/doc/tutorial/getting-started

## 02-create-module

- https://golang.org/doc/tutorial/create-module
- https://golang.org/doc/tutorial/call-module-code

## 03-error-handling

- https://golang.org/doc/tutorial/handle-errors

## 04-maps-slides

- https://golang.org/doc/tutorial/greetings-multiple-people

## 05-testing

- https://golang.org/doc/tutorial/add-a-test

## 06-tview

Playing with [tview](https://github.com/rivo/tview)

## 07-termui

Playing with [termui](https://github.com/gizak/termui)

## no-folder: compile and install

Compile and install (can use `05-testing` folder to follow the tutorial): https://golang.org/doc/tutorial/compile-install

```bash
export PATH=$PATH:$HOME/go/bin
go mod init github.com/ckinan.com/learning
go mod tidy
go build
go install
```

## Refs

### Quotes

> Go code is grouped into packages, and packages are grouped into modules.

> In Go, a function whose name starts with a capital letter can be called by a function not in the same package. This is known in Go as an exported name.

> [... a function that] starts with a lowercase letter [... is] accessible only to code in its own package (in other words, it's not exported).

### Links

- Visual Studio Code Plugin: https://marketplace.visualstudio.com/items?itemName=golang.Go
- For error message in Visual Studio Code saying: `Error loading workspace: You are outside of a module and outside of $GOPATH/src. If you are using modules, please open your editor to a directory in your module`: https://github.com/microsoft/vscode-go/issues/3086#issuecomment-597423488
- How to debug Go code in Visual Studio Code: https://www.digitalocean.com/community/tutorials/debugging-go-code-with-visual-studio-code
- CLI: https://github.com/spf13/cobra
- TUI: https://github.com/rivo/tview
- https://github.com/urfave/cli
- https://github.com/spf13/cobra