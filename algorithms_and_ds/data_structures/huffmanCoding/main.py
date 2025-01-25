with open('C:/Users/Artem/Desktop/huffman.in.txt') as inputFile, open('C:/Users/Artem/Desktop/huffman.out.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    nums = list(map(int, inputFile.readline().strip().split()))
    max_val = 1e9 + 1
    sums = [0]
    sums[0] = nums[0] + nums[1]
    answer = sums[0]
    index_nums = 2
    index_sums = 0
    last_index = 0
    while index_nums < n - 1:
        val = max_val if len(sums) == index_sums + 1 else sums[index_sums + 1]
        val_1 = nums[index_nums] + nums[index_nums + 1]
        val_2 = nums[index_nums] + sums[index_sums]
        val_3 = sums[index_sums] + val
        if val_1 <= val_2 and val_1 <= val_2:
            sums.append(val_1)
            answer += val_1
            index_nums += 2
        elif val_2 <= val_1 and val_2 <= val_3:
            sums.append(val_2)
            index_sums += 1
            index_nums += 1
            answer += val_2
        else:
            sums.append(val_3)
            index_sums += 2
            answer += val_3
    while index_nums < n:
        val = max_val if len(sums) == index_sums + 1 else sums[index_sums + 1]
        val_2 = nums[index_nums] + sums[index_sums]
        val_3 = sums[index_sums] + val
        if val_2 <= val_3:
            sums.append(val_2)
            index_sums += 1
            answer += val_2
            index_nums += 1
        else:
            sums[index_sums + 2] = val_3
            index_sums += 2
            answer += val_3
    if index_sums != len(sums) - 1:
        while index_sums != len(sums) - 1:
            answer += sums[index_sums] + sums[index_sums + 1]
            sums.append(sums[index_sums] + sums[index_sums + 1])
            index_sums += 2
    outputFile.write(str(answer))
