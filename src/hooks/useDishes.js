import {useCallback, useEffect, useState} from "react";
import dishRepository from "../repository/dishRepository.js";

const initialState = {
    dishes: [],
    loading: true,
};

const useDishes = () => {
    const [state, setState] = useState(initialState);

    const fetchDishes = useCallback(() => {
        setState(initialState);
        dishRepository
            .findAll()
            .then((response) => {
                setState({
                    "dishes": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        dishRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new dish.");
                fetchDishes();
            })
            .catch((error) => console.log(error));
    }, [fetchDishes]);

    const onEdit = useCallback((id, data) => {
        dishRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the dish with ID ${id}.`);
                fetchDishes();
            })
            .catch((error) => console.log(error));
    }, [fetchDishes]);

    const onDelete = useCallback((id) => {
        dishRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the dish with ID ${id}.`);
                fetchDishes();
            })
            .catch((error) => console.log(error));
    }, [fetchDishes]);

    useEffect(() => {
        fetchDishes();
    }, [fetchDishes]);

    return {...state, onAdd, onEdit, onDelete};
};

export default useDishes;