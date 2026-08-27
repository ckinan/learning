extends Area2D

signal hit
@export var speed = 400 # how fast the player will move (pixels/sec).
var screen_size # size of the game window

func _ready():
	screen_size = get_viewport_rect().size
	hide()
	

func start(pos):
	position = pos
	show()
	$CollisionShape2D.disabled = false

func _process(delta):
	var velocity = Vector2.ZERO # the player's movement vector
	if Input.is_action_pressed("move_right"):
			velocity.x += 1
	if Input.is_action_pressed("move_left"):
			velocity.x -= 1
	if Input.is_action_pressed("move_down"):
			velocity.y += 1
	if Input.is_action_pressed("move_up"):
			velocity.y -= 1

	if velocity.length() > 0:
		velocity = velocity.normalized() * speed
		$AnimatedSprite2D.play()
	else:
		$AnimatedSprite2D.stop()

	position += velocity * delta
	position = position.clamp(Vector2.ZERO, screen_size)

	if velocity.x != 0:
		$AnimatedSprite2D.animation = "walk"
		$AnimatedSprite2D.flip_v = false
		# See the note below about the following boolean assignment
		$AnimatedSprite2D.flip_h = velocity.x < 0
	elif velocity.y != 0:
		$AnimatedSprite2D.animation = "up"
		$AnimatedSprite2D.flip_v = velocity.y > 0

func _on_body_entered(body: Node2D) -> void:
	hide() # player disappears after being hit
	hit.emit()
	# must be deferred as we can't change physics properties on a physics callback
	$CollisionShape2D.set_deferred("disabled", true)
