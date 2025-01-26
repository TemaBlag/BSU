import timeit


class Solution:
    def __init__(self):
        self.n = None
        self.m = None
        self.min_time = float('inf')
        self.time = 0
        self.busy = 0
        self.busy_work = set()
        self.works = []
        self.plan = []
        self.workers = []
        self.matrix = []
        self.all_free_works = []
        self.all_works = set()

    def free_work(self):
        self.time += self.min_time
        new_min_time = float('inf')
        for i in range(len(self.workers)):
            if self.workers[i]:
                self.workers[i][0] -= self.min_time
                if self.workers[i][0]:
                    if self.workers[i][0] < new_min_time:
                        new_min_time = self.workers[i][0]
                else:
                    self.busy -= 1
                    self.busy_work.remove(self.workers[i][1])
                    self.workers[i] = 0
        self.min_time = new_min_time

    def get_work(self, ind):
        indexes = []
        for i in range(len(self.works[ind])):
            time, worker = self.works[ind][i]
            if not self.workers[worker]:
                self.all_free_works[worker].remove(ind)
                if time == 0:
                    indexes.append(i)
                    self.plan.append(f"{ind + 1} {worker + 1}")
                    continue
                self.busy_work.add(ind)
                self.busy += 1
                self.workers[worker] = [time, ind]
                if self.min_time > time:
                    self.min_time = time
                for i in range(len(indexes)):
                    self.works[ind].pop(indexes[i] - i)
                self.works[ind].pop(i - len(indexes))
                self.plan.append(f"{ind + 1} {worker + 1}")
                return False
        for i in range(len(indexes)):
            self.works[ind].pop(indexes[i] - i)
        return True

    def read_data(self):
        with open('input', 'r') as f:
            self.n, self.m = list(map(int, f.readline().strip().split()))
            for line in f.readlines():
                temp = list(map(int, line.strip().split()))
                self.matrix.append(temp)
                self.works.append(sorted([[val, i]
                                          for i, val in enumerate(temp)], reverse=True))
            self.all_works = {i for i in range(self.n)}
            self.all_free_works = [{i for i in range(self.n)} for i in range(self.m)]
            self.workers = [0] * self.m

    def work(self, values):
        k = self.m * self.n
        while len(self.plan) != k:
            flag = True
            for j in values:
                if len(self.busy_work) == self.n or len(self.busy_work) == self.m:
                    self.free_work()
                    self.work(self.all_works - self.busy_work)
                    return
                if j not in self.busy_work:
                    flag &= self.get_work(j)
            if flag:
                self.free_work()
                self.work(self.all_works - self.busy_work)
        t = 0
        for val in self.workers:
            if val and val[0] > t:
                t = val[0]
        self.time += t

    def write_data(self):
        with open('output', 'w') as f:
            f.write(str(self.time))
            f.write("".join(self.plan))

sol = Solution()
sol.read_data()
sol.work(sol.all_works)
sol.write_data()


# elapsed_time = timeit.timeit(code_to_test, number=1)
# print('Elapsed time: ', elapsed_time)
