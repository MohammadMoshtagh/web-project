class Currency:

    def __init__(self, code: str, name: str):
        self.code = code
        self.name = name

    def __str__(self) -> str:
        return f'{{"name": {self.name}, "code": {self.code}}}'