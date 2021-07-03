# golang-practice

## 01-getting-started

```go
go mod init example.com/hello
go run .
```

### Links

- https://golang.org/doc/tutorial/getting-started

## 02-create-module

> Go code is grouped into packages, and packages are grouped into modules.
>
> In Go, a function whose name starts with a capital letter can be called by a function not in the same package. This is known in Go as an exported name.

### Links

- https://golang.org/doc/tutorial/create-module
- https://golang.org/doc/tutorial/call-module-code

## 03-error-handling

- https://golang.org/doc/tutorial/handle-errors

## Refs

- Visual Studio Code Plugin: https://marketplace.visualstudio.com/items?itemName=golang.Go
- For error message in Visual Studio Code saying: `Error loading workspace: You are outside of a module and outside of $GOPATH/src. If you are using modules, please open your editor to a directory in your module`: https://github.com/microsoft/vscode-go/issues/3086#issuecomment-597423488