import { useGetData } from './useGetData';
import usePostData from './usePostData';
import usePatchData from './usePatchData';
import { toast } from 'react-toastify';
import axios from 'axios';
import instance from '../api/instance';

const header = {
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
    Authorization: localStorage.ACCESS,
  },
};

// 4.4, 4.5, 5.3
const useTayoGet = async (url, data) => {
  await useGetData(url, data, header).then(res => {
    if (res.response.status === 401) {
      toast.warning('오류오류오류');
    }
  });
};

// 4.1
const useTayoCreate = async (url, data) => {
  try {
    const response = await axios.post(url, data, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Authorization: localStorage.getItem('ACCESS'),
      },
    });
    toast.success('게시물 생성에 성공했습니다.');
    return response;
  } catch (error) {
    toast.warning('게시물 생성에 실패했습니다.');
  }
};

const useTayoEdit = async (url, data) => {
  try {
    const response = await instance.patch(url, data);
    return response.data.data;
  } catch (error) {
    toast.warning('게시물 수정에 실패했습니다.');
    // console.log(error);
  }
};

const useTayoRequest = async (url, data) => {
  try {
    const response = await instance.post(url, data);
    toast.success('신청 완료');
    return response;
  } catch (error) {
    if (error.response.status === 403) {
      toast.warning('본인 게시물에 신청할 수 없습니다.');
    } else if (error.response.status === 409) {
      toast.warning('이미 신청된 게시물입니다.');
    } else {
      toast.warning('신청에 실패했습니다.');
    }
    console.log(error);
  }
};

const useTayoInvite = async (url, data) => {
  try {
    const response = await instance.post(url, data, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Authorization: localStorage.getItem('ACCESS'),
      },
    });
    toast.success('초대 완료');
    return response;
  } catch (error) {
    if (error.response.status === 403) {
      toast.warning('본인 게시물에 초대할 수 없습니다.');
    } else if (error.response.status === 409) {
      toast.warning('이미 초대된 게시물입니다.');
    } else {
      toast.warning('초대에 실패했습니다.');
    }
    console.log(error);
  }
};

// 6.1 6.2
const useTayoAccept = async (url, data) => {
  await usePostData(url, data, header).then(res => {
    if (res.response.status === 401) {
      toast.warning('신청에 실패했습니다.');
    }
  });
};

export { useTayoGet, useTayoCreate, useTayoEdit, useTayoRequest, useTayoInvite, useTayoAccept };
