package main

import (
	"crypto/rsa"
	"fmt"
	"log"
	"net/http"
	"os"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/golang-jwt/jwt/v5"
)

var privateKey *rsa.PrivateKey
var publicKey *rsa.PublicKey

type Credentials struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

func loadKeys() error {
	// Load private key
	rawPrivateKey, err := os.ReadFile("./private.pem")
	if err != nil {
		return err
	}
	privateKey, err = jwt.ParseRSAPrivateKeyFromPEM(rawPrivateKey)
	if err != nil {
		return err
	}

	// Load public key
	rawPublicKey, err := os.ReadFile("./public.pem")
	if err != nil {
		return err
	}
	publicKey, err = jwt.ParseRSAPublicKeyFromPEM(rawPublicKey)
	if err != nil {
		return err
	}

	return nil
}

func jwtMiddleware(c *gin.Context) {
	tokenString := c.GetHeader("Authorization")
	log.Println("tokenString:", tokenString)
	if tokenString == "" {
		c.JSON(http.StatusUnauthorized, gin.H{"error": "Missing token"})
		c.Abort()
		return
	}

	tokenString = tokenString[len("Bearer "):]

	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodRSA); !ok {
			return nil, gin.Error{Err: fmt.Errorf("unexpected signing method")}
		}
		return publicKey, nil
	})

	if err != nil || !token.Valid {
		c.JSON(http.StatusUnauthorized, gin.H{"error": "Invalid token"})
		c.Abort()
		return
	}

	c.Next()
}

func loginHandler(c *gin.Context) {
	var creds Credentials
	if err := c.ShouldBindJSON(&creds); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid request"})
		return
	}

	// For demonstration, accept any username/password
	if creds.Username == "" || creds.Password == "" {
		c.JSON(http.StatusUnauthorized, gin.H{"error": "Invalid credentials"})
		return
	}

	token := jwt.NewWithClaims(jwt.SigningMethodRS256, jwt.MapClaims{
		"username": creds.Username,
		"exp":      time.Now().Add(time.Hour).Unix(),
	})
	tokenString, err := token.SignedString(privateKey)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": "Could not generate token"})
		return
	}

	c.JSON(http.StatusOK, gin.H{"token": tokenString})
}

func main() {
	if err := loadKeys(); err != nil {
		log.Fatalf("Failed to load keys: %v", err)
	}

	router := gin.Default()

	// Public group
	public := router.Group("/public")
	public.GET("/ping", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{
			"message": "pong",
		})
	})

	// Login endpoint
	router.POST("/login", loginHandler)

	// Protected group
	protected := router.Group("/protected")
	protected.Use(jwtMiddleware)
	{
		protected.GET("/data", func(c *gin.Context) {
			c.JSON(http.StatusOK, gin.H{
				"data": "This is protected data",
			})
		})
	}

	router.Run(":8080")
}
