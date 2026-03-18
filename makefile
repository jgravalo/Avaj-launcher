all:
	javac @sources.txt
	java avaj.Simulator scenario.txt

clean:
	rm avaj/*.class

fclean: clean
	rm simulation.txt

re:	fclean all

.PHONY: all clean fclean re
