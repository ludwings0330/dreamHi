import jwtApi from "util/JwtApi";

const updateProfileFile = async (actorId, payload, success, fail) => {
    await jwtApi.post(`/api/actors/${actorId}/media`, payload)
        .then(success)
        .catch(fail);
}

export {
    updateProfileFile,
}
22