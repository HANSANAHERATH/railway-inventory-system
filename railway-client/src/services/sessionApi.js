import axios from 'axios';

export default {
    Items: {
        submit: payload =>
            axios
                .post(
                    `/items`,
                    {
                        ...payload,
                    })
                .then(res => res.data),
        update: payload =>
            axios
                .put(
                    `/items/${payload.id}`,
                    {
                        ...payload,
                    })
                .then(res => res.data),
        delete: payload =>
            axios
                .delete(
                    `/items/${payload.id}`,

                    {
                        headers: {
                            Authorization: sessionStorage.getItem('token'),
                        },
                        data: {
                            ...payload,
                        },
                    }
                )
                .then(res => res.data),
        getAll: payload =>
            axios
                .get(`/items/all/${payload.categoryId}/${payload.pageSize}/${payload.page}`,
                {
                    ...payload,
                })
                .then(res => res.data),
    },
    units: {
        unitTypes: () =>
            axios
                .get('/items/unitList')
                .then(res => res.data),
    },
    Category: {
        categoryList: () =>
            axios
                .get('/items/itemCategoryList')
                .then(res => res.data),
    }
};
