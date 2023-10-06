from typing import List
import pandas as pd


def createDataframe(student_data: List[List[int]]) -> pd.DataFrame:
    return pd.DataFrame([{"student_id": row[0], "age": row[1]} for row in student_data])
