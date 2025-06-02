import axiosInstance from "../axios/axios.js";

const shoppingCartRepository = {
    getActive: async () => {
        return await axiosInstance.get("/shopping-cart");
    },
    addToCart: async (id) => {
        return await axiosInstance.post(`/shopping-cart/add-product/${id}`);
    },
};

export default shoppingCartRepository;