def main():
    print("Welcome To Bash Game!!")
    print("\nWhat is Bash Game?\n\n"
          "There is only a pile of n items, and two people take turns from this pile of items.\n"
          "It is stipulated to take at least one at a time, and at most m.\n"
          "The people who take all the item will LOSE.\n")
    pve_mode()

def pve_mode():
    total_item = 20
    max_item_per_round = 3
    ask = ""
    print("PVE Mode\n")
    print("By DEFAULT: n = 20, m = 3, minimum per round = 1")
    ask = input("Do you wish to change the \"Total Item\"(n) and \"Maximum Item Taken Each Round\"(m)? (Y = Yes/ N = No)")
    if ask == "Y" or ask == "y":
        total_item = int(input("\nEnter n: "))
        max_item_per_round = int(input("\nEnter m: "))
        if total_item < 1:
            print("n less than 1, set to DEFAULT(n = 20)!")
            total_item = 20
        if max_item_per_round < 1:
            print("m less than 1, set to DEFAULT(m = 3)!")
            max_item_per_round = 3
        if max_item_per_round > total_item:
            print("Warning! m > n")
            print("Your m and n is using illegal value! The Bash Game will Stop!")
            exit(0)
    elif ask == "N" or ask == "n":
        print("Using DEFAULT")
    else:
        print("Your input either \"Y\" or \"N\", set to DEFAULT")

    print("\nGame Start!!\n")
    print("n = ", total_item, ", m = ", max_item_per_round, ", minimum per round = 1\n")
    end = False
    player_first = None
    a = max_item_per_round + 1
    k = total_item // a
    b = total_item - (a * k)
    c_first_taken = 0
    if b == 1:
        player_first = True
    else:
        player_first = False
    if not player_first:
        if b == 0:
            c_first_taken = max_item_per_round
        else:
            c_first_taken = b - 1
    first_round = True
    number = 0
    c_number = 0
    while not end:
        if player_first:
            print("Current Item Number: ", total_item)
            number = player_1(max_item_per_round, total_item)
            total_item -= number
            print("Current Item Number: ", total_item)
            if total_item == 0:
                print("Player 1 LOSE")
                end = True
                break
            c_number = computer(max_item_per_round, number)
            total_item -= c_number
            if total_item == 0:
                print("Computer LOSE")
                end = True
                break
        else:
            if first_round:
                number = c_first_taken
                total_item -= c_first_taken
                first_round = False
            else:
                c_number = computer(max_item_per_round, number)
                total_item -= c_number
                if total_item == 0:
                    print("Computer LOSE")
                    end = True
                    break
            print("Current Item Number: ", total_item)
            number = player_1(max_item_per_round, total_item)
            total_item -= number
            print("Current Item Number: ", total_item)
            if total_item == 0:
                print("Player 1 LOSE")
                end = True
                break
    print("The End")

def player_1(max_item_per_round, total_item):
    number = 0
    while number < 1 or number > max_item_per_round:
        try:
            number = int(input("\nPlayer 1, enter number of items taken: "))
            if number < 1 or number > max_item_per_round:
                print("Invalid number, the number of items must be between 1 and", max_item_per_round)
        except ValueError:
            print("Invalid input, please enter a number.")
    return number

def computer(max_item_per_round, number):
    c_number = (max_item_per_round - number + 1) % (max_item_per_round + 1)
    if c_number == 0:
        c_number = random.randint(1, max_item_per_round)
        print("Computer took ", c_number, " items")
    return c_number
    
if __name__ == '__main__':
    main()
