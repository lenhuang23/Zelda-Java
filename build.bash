#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Link.java Brick.java Sprite.java Pot.java Boomerang.java Json.java 
java Game
