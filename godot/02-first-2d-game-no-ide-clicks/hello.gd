extends SceneTree

func _init():
	await process_frame
	var game_world = Node2D.new()
	root.add_child(game_world)
	var sprite = Sprite2D.new()
	sprite.texture = load("res://icon.svg")
	sprite.position = Vector2(240, 360)
	sprite.name = "CenteredIcon"
	game_world.add_child(sprite)

func _process(delta):
	if Input.is_key_pressed(KEY_ESCAPE):
		quit()
	return false
