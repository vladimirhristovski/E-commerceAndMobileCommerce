import axiosInstance from "../axios/axios.js";

const accommodationRepository = {
    findAll: async () => {
        return await axiosInstance.get("/accommodations");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/accommodations/${id}`);
    },
    add: async (data) => {
        return await axiosInstance.post("/accommodations/add", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/accommodations/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/accommodations/delete/${id}`);
    },
};

export default accommodationRepository;
